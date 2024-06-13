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
 * 全查询
 *
 * @param orders 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(Orders orders) {
        return this.ordersService.queryAll(orders);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.ordersService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param orders 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody Orders orders) {
        return this.ordersService.insert(orders);
        }

/**
 * 编辑数据
 *
 * @param orders 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody Orders orders) {
        return this.ordersService.update(orders);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.ordersService.deleteById(id);
        }

        }


