package com.xiaohu.hospital.controller;

import com.xiaohu.hospital.entity.SetMealDetailed;
import com.xiaohu.hospital.service.SetMealDetailedService;
import com.xiaohu.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SetMealDetailed)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@RestController
@RequestMapping("setMealDetailed")
public class SetMealDetailedController {
/**
 * 服务对象
 */
@Resource
private SetMealDetailedService setMealDetailedService;

/**
 * 全查询
 *
 * @param setMealDetailed 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(SetMealDetailed setMealDetailed) {
        return this.setMealDetailedService.queryAll(setMealDetailed);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.setMealDetailedService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param setMealDetailed 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody SetMealDetailed setMealDetailed) {
        return this.setMealDetailedService.insert(setMealDetailed);
        }

/**
 * 编辑数据
 *
 * @param setMealDetailed 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody SetMealDetailed setMealDetailed) {
        return this.setMealDetailedService.update(setMealDetailed);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.setMealDetailedService.deleteById(id);
        }

        }


