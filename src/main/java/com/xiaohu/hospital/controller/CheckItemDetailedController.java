package com.xiaohu.hospital.controller;

import com.xiaohu.hospital.entity.CheckItemDetailed;
import com.xiaohu.hospital.service.CheckItemDetailedService;
import com.xiaohu.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CheckItemDetailed)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@RestController
@RequestMapping("checkItemDetailed")
public class CheckItemDetailedController {
/**
 * 服务对象
 */
@Resource
private CheckItemDetailedService checkItemDetailedService;

/**
 * 全查询
 *
 * @param checkItemDetailed 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(CheckItemDetailed checkItemDetailed) {
        return this.checkItemDetailedService.queryAll(checkItemDetailed);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.checkItemDetailedService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param checkItemDetailed 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody CheckItemDetailed checkItemDetailed) {
        return this.checkItemDetailedService.insert(checkItemDetailed);
        }

/**
 * 编辑数据
 *
 * @param checkItemDetailed 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody CheckItemDetailed checkItemDetailed) {
        return this.checkItemDetailedService.update(checkItemDetailed);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.checkItemDetailedService.deleteById(id);
        }

        }


