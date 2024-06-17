package com.hospital.controller;

import com.hospital.entity.Orders;
import com.hospital.service.OrdersService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Orders)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@RestController
@RequestMapping("orders")
public class OrdersController {
        /**
         * 服务对象
         */
        @Resource
        private OrdersService ordersService;

        /**
         * 生成订单
         *
         * @param orders 订单信息
         * @return 订单信息
         */
        @PostMapping("createOrder")
        public Result create(@RequestBody Orders orders) {
                return ordersService.insert(orders);
        }

        /**
         * 查询订单
         * @param orders
         * @return
         */
        @PostMapping("checkOrder")
        public Result check(@RequestBody Orders orders) {
                return ordersService.check(orders);
        }
        /**
         * 取消订单
         * @param ordersId
         * @return
         */
        @GetMapping("cancelOrder/{ordersId}")
        public Result cancel(@PathVariable("ordersId") Integer ordersId) {
                return ordersService.cancel(ordersId);
        }
}
