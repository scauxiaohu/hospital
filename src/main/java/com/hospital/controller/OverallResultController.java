package com.hospital.controller;

import com.hospital.entity.OverallResult;
import com.hospital.service.OverallResultService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (OverallResult)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@RestController
@RequestMapping("overallResult")
public class OverallResultController {
/**
 * 服务对象
 */
@Resource
private OverallResultService overallResultService;

/**
 * 全查询
 *
 * @param overallResult 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(OverallResult overallResult) {
        return this.overallResultService.queryAll(overallResult);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.overallResultService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param overallResult 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody OverallResult overallResult) {
        return this.overallResultService.insert(overallResult);
        }

/**
 * 编辑数据
 *
 * @param overallResult 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody OverallResult overallResult) {
        return this.overallResultService.update(overallResult);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.overallResultService.deleteById(id);
        }

        }


