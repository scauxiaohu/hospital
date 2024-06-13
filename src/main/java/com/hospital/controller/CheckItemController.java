package com.hospital.controller;

import com.hospital.entity.CheckItem;
import com.hospital.service.CheckItemService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CheckItem)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@RestController
@RequestMapping("checkItem")
public class

CheckItemController {
/**
 * 服务对象
 */
@Resource
private CheckItemService checkItemService;

/**
 * 全查询
 *
 * @param checkItem 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(CheckItem checkItem) {
        return this.checkItemService.queryAll(checkItem);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.checkItemService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param checkItem 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody CheckItem checkItem) {
        return this.checkItemService.insert(checkItem);
        }

/**
 * 编辑数据
 *
 * @param checkItem 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody CheckItem checkItem) {
        return this.checkItemService.update(checkItem);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.checkItemService.deleteById(id);
        }

        }


