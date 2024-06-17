package com.hospital.service.impl;

import com.hospital.entity.Orders;
import com.hospital.entity.SetMeal;
import com.hospital.mapper.HospitalMapper;
import com.hospital.mapper.OrdersMapper;
import com.hospital.mapper.SetMealMapper;
import com.hospital.response.OrderInfoResponse;
import com.hospital.service.HospitalService;
import com.hospital.service.OrdersService;
import com.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Orders)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
@Resource
private OrdersMapper ordersMapper;
@Resource
private HospitalMapper hospitalMapper;
@Resource
private SetMealMapper setMealMapper;
/**
 * 通过ID查询单条数据
 *
 * @param orderId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer orderId) {
        return Result.success(this.ordersMapper.queryById(orderId));
        }

/**
 * 全查询
 *
 * @param orders 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(Orders orders) {
        return Result.success(this.ordersMapper.queryAll(orders));
        }

/**
 * 新增数据
 *
 * @param orders 实例对象
 * @return 实例对象
 */
@Override
public Result insert(Orders orders) {
        orders.setState(1);
        List<Orders> ordersList = ordersMapper.queryAll(orders);
        if (ordersList.size() > 0) {
                return Result.error("今日提交预约，请勿重复提交");
        }
        Integer result = ordersMapper.insert(orders);
        if (result == 1) {
                return Result.success(orders);
        } else {
                return Result.error("新增失败");
        }
}
/**
 * 修改数据
 *
 * @param orders 实例对象
 * @return 实例对象
 */
@Override
public Result update(Orders orders) {
        this.ordersMapper.update(orders);
        return Result.success(this.queryById(orders.getOrderId()));
        }

/**
 * 通过主键删除数据
 *
 * @param orderId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer orderId) {
        boolean del = this.ordersMapper.deleteById(orderId) > 0;
        return Result.success(del);
        }

        /**
         * 通过userId查询订单信息
         * @param orders
         * @return
         */
        public  Result check(Orders orders)
        {
                orders.setState(1);
        List<Orders> ordersList = ordersMapper.queryAll(orders);
        List<OrderInfoResponse> orderInfoResponses = new ArrayList<>();
        for (Orders order : ordersList) {
                OrderInfoResponse orderInfoResponse = new OrderInfoResponse();
                orderInfoResponse.setOrderId(order.getOrderId());
                orderInfoResponse.setOrderDate(order.getOrderDate());
                orderInfoResponse.setUserId(order.getUserId());
                orderInfoResponse.setHpId(order.getHpId());
                orderInfoResponse.setSmId(order.getSmId());
                orderInfoResponse.setState(order.getState());
                orderInfoResponse.setHospital(hospitalMapper.queryById(order.getHpId()));
                orderInfoResponse.setSetMeal(setMealMapper.queryById(order.getSmId()));
                orderInfoResponses.add(orderInfoResponse);
        }
        return Result.success(orderInfoResponses);
        }

        /**
         * 取消订单
         * @param ordersId
         * @return
         */
        public Result cancel(Integer ordersId)
        {
                Orders orders = new Orders();
                orders.setOrderId(ordersId);
                ordersMapper.queryAll(orders);
                orders.setState(0);
                Integer num=ordersMapper.update(orders);
                return Result.success(num);
        }

        }

