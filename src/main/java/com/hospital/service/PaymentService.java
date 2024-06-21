package com.hospital.service;

import com.hospital.entity.Payment;
import com.hospital.util.Result;

/**
 * (Payment)表服务接口
 *
 * @author xiaohu
 * @since 2024-06-21 16:11:10
 */
public interface PaymentService {

        /**
         * 通过ID查询单条数据
         *
         * @param opId 主键
         * @return 实例对象
         */
        Result queryById(Integer opId);

        /**
         * 全查询
         *
         * @param payment 筛选条件
         * @return 查询结果
         */
        Result queryAll(Payment payment);

        /**
         * 新增数据
         *
         * @param payment 实例对象
         * @return 实例对象
         */
        Result insert(Payment payment);

        /**
         * 修改数据
         *
         * @param payment 实例对象
         * @return 实例对象
         */
        Result update(Payment payment);

        /**
         * 通过主键删除数据
         *
         * @param opId 主键
         * @return 是否成功
         */
        Result deleteById(Integer opId);

        }

