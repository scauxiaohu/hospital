package com.xiaohu.hospital.service;

import com.xiaohu.hospital.entity.SetMeal;
import com.xiaohu.hospital.util.Result;

/**
 * (SetMeal)表服务接口
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
public interface SetMealService {

        /**
         * 通过ID查询单条数据
         *
         * @param smId 主键
         * @return 实例对象
         */
        Result queryById(Integer smId);

        /**
         * 全查询
         *
         * @param setMeal 筛选条件
         * @return 查询结果
         */
        Result queryAll(SetMeal setMeal);

        /**
         * 新增数据
         *
         * @param setMeal 实例对象
         * @return 实例对象
         */
        Result insert(SetMeal setMeal);

        /**
         * 修改数据
         *
         * @param setMeal 实例对象
         * @return 实例对象
         */
        Result update(SetMeal setMeal);

        /**
         * 通过主键删除数据
         *
         * @param smId 主键
         * @return 是否成功
         */
        Result deleteById(Integer smId);

        }

