package com.xiaohu.hospital.service.impl;

import com.xiaohu.hospital.entity.SetMealDetailed;
import com.xiaohu.hospital.mapper.SetMealDetailedMapper;
import com.xiaohu.hospital.service.SetMealDetailedService;
import com.xiaohu.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (SetMealDetailed)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Service("setMealDetailedService")
public class SetMealDetailedServiceImpl implements SetMealDetailedService {
@Resource
private SetMealDetailedMapper setMealDetailedMapper;

/**
 * 通过ID查询单条数据
 *
 * @param sdId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer sdId) {
        return Result.success(this.setMealDetailedMapper.queryById(sdId));
        }

/**
 * 全查询
 *
 * @param setMealDetailed 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(SetMealDetailed setMealDetailed) {
        return Result.success(this.setMealDetailedMapper.queryAll(setMealDetailed));
        }

/**
 * 新增数据
 *
 * @param setMealDetailed 实例对象
 * @return 实例对象
 */
@Override
public Result insert(SetMealDetailed setMealDetailed) {
        this.setMealDetailedMapper.insert(setMealDetailed);
        return Result.success(setMealDetailed);
        }

/**
 * 修改数据
 *
 * @param setMealDetailed 实例对象
 * @return 实例对象
 */
@Override
public Result update(SetMealDetailed setMealDetailed) {
        this.setMealDetailedMapper.update(setMealDetailed);
        return Result.success(this.queryById(setMealDetailed.getSdId()));
        }

/**
 * 通过主键删除数据
 *
 * @param sdId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer sdId) {
        boolean del = this.setMealDetailedMapper.deleteById(sdId) > 0;
        return Result.success(del);
        }
        }

