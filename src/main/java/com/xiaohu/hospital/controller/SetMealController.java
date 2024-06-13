package com.xiaohu.hospital.controller;

import com.xiaohu.hospital.entity.SetMeal;
import com.xiaohu.hospital.service.SetMealService;
import com.xiaohu.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SetMeal)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@RestController
@RequestMapping("setMeal")
public class SetMealController {
/**
 * 服务对象
 */
@Resource
private SetMealService setMealService;

/**
 * 全查询
 *
 * @param setMeal 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(SetMeal setMeal) {
        return this.setMealService.queryAll(setMeal);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.setMealService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param setMeal 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody SetMeal setMeal) {
        return this.setMealService.insert(setMeal);
        }

/**
 * 编辑数据
 *
 * @param setMeal 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody SetMeal setMeal) {
        return this.setMealService.update(setMeal);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.setMealService.deleteById(id);
        }

        }


