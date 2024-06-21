package com.hospital.service.impl;

import com.hospital.entity.Payment;
import com.hospital.mapper.PaymentMapper;
import com.hospital.service.PaymentService;
import com.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Payment)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-21 16:11:10
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
@Resource
private PaymentMapper paymentMapper;

/**
 * 通过ID查询单条数据
 *
 * @param opId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer opId) {
        return Result.success(this.paymentMapper.queryById(opId));
        }

/**
 * 全查询
 *
 * @param payment 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(Payment payment) {
        return Result.success(this.paymentMapper.queryAll(payment));
        }

/**
 * 新增数据
 *
 * @param payment 实例对象
 * @return 实例对象
 */
@Override
public Result insert(Payment payment) {
        this.paymentMapper.insert(payment);
        return Result.success(payment);
        }

/**
 * 修改数据
 *
 * @param payment 实例对象
 * @return 实例对象
 */
@Override
public Result update(Payment payment) {
        this.paymentMapper.update(payment);
        return Result.success(this.queryById(payment.getOpId()));
        }

/**
 * 通过主键删除数据
 *
 * @param opId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer opId) {
        boolean del = this.paymentMapper.deleteById(opId) > 0;
        return Result.success(del);
        }
        }

