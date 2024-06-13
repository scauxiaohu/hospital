package com.xiaohu.hospital.controller;

import com.xiaohu.hospital.entity.Cireport;
import com.xiaohu.hospital.service.CireportService;
import com.xiaohu.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Cireport)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@RestController
@RequestMapping("cireport")
public class CireportController {
/**
 * 服务对象
 */
@Resource
private CireportService cireportService;

/**
 * 全查询
 *
 * @param cireport 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(Cireport cireport) {
        return this.cireportService.queryAll(cireport);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.cireportService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param cireport 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody Cireport cireport) {
        return this.cireportService.insert(cireport);
        }

/**
 * 编辑数据
 *
 * @param cireport 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody Cireport cireport) {
        return this.cireportService.update(cireport);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.cireportService.deleteById(id);
        }

        }


