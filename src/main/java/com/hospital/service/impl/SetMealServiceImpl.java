package com.hospital.service.impl;

import com.hospital.entity.CheckItem;
import com.hospital.entity.SetMealDetailed;
import com.hospital.entity.Users;
import com.hospital.mapper.CheckItemMapper;
import com.hospital.mapper.SetMealDetailedMapper;
import com.hospital.mapper.UsersMapper;
import com.hospital.response.MealInfoResponse;
import com.hospital.service.SetMealService;
import com.hospital.entity.SetMeal;
import com.hospital.mapper.SetMealMapper;
import com.hospital.util.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hospital.util.Status.*;

/**
 * (SetMeal)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Service("setMealService")
public class SetMealServiceImpl implements SetMealService {
@Resource
private SetMealMapper setMealMapper;
@Resource
private SetMealDetailedMapper setMealDetailedMapper;
@Resource
private CheckItemMapper checkItemMapper;
@Resource
private UsersMapper usersMapper;

/**
 * 通过ID查询单条数据
 *
 * @param smId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer smId) {
        return Result.success(this.setMealMapper.queryById(smId));
        }

/**
 * 全查询
 *
 * @param setMeal 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(SetMeal setMeal) {
        return Result.success(this.setMealMapper.queryAll(setMeal));
        }

/**
 * 新增数据
 *
 * @param setMeal 实例对象
 * @return 实例对象
 */
@Override
public Result insert(SetMeal setMeal) {
        this.setMealMapper.insert(setMeal);
        return Result.success(setMeal);
        }

/**
 * 修改数据
 *
 * @param setMeal 实例对象
 * @return 实例对象
 */
@Override
public Result update(SetMeal setMeal) {
        this.setMealMapper.update(setMeal);
        return Result.success(this.queryById(setMeal.getSmId()));
        }

/**
 * 通过主键删除数据
 *
 * @param smId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer smId) {
        boolean del = this.setMealMapper.deleteById(smId) > 0;
        return Result.success(del);
        }

        /**
         * 根据套餐类型查询套餐信息
         * @param type
         * @return
         */
        @Override
       public Result getSetMealsByType(Integer type,String userId)
        {
                Users user = usersMapper.queryById(userId);
                List<SetMeal> setMeals = setMealMapper.getSetMealsByType(type);
                if(setMeals.size() == 0||setMeals == null)
                {
                        return Result.error( SETMEAL_FIND_NOT_EXIST);
                }
                List<MealInfoResponse> mealInfoResponses = new ArrayList<>();
                for(SetMeal setMeal : setMeals) {

                        MealInfoResponse mealInfoResponse = new MealInfoResponse();
                        mealInfoResponse.setName(setMeal.getName());
                        Integer price = setMeal.getPrice();
                        if(user.getUserType() == 2)
                        {
                                price = 0;
                        }
                        mealInfoResponse.setPrice(price);
                        mealInfoResponse.setSmId(setMeal.getSmId());
                        mealInfoResponse.setType(setMeal.getType());

                        List<SetMealDetailed> setMealDetaileds;
                        setMealDetaileds = setMealDetailedMapper.queryBySmId(setMeal.getSmId());

                        if(setMealDetaileds.size() != 0||setMealDetaileds != null) {
                                for(SetMealDetailed setMealDetailed : setMealDetaileds) {
                                      CheckItem checkItem = checkItemMapper.queryById(setMealDetailed.getCiId());
                                      mealInfoResponse.getCheckItems().add(checkItem);
                                }
                                mealInfoResponses.add(mealInfoResponse);
                        }
                        }
                return Result.success(mealInfoResponses);
                }


        }

