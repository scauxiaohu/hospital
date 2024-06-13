package com.hospital.controller;

import com.hospital.entity.CidetailedReport;
import com.hospital.service.CidetailedReportService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CidetailedReport)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@RestController
@RequestMapping("cidetailedReport")
public class CidetailedReportController {
/**
 * 服务对象
 */
@Resource
private CidetailedReportService cidetailedReportService;

/**
 * 全查询
 *
 * @param cidetailedReport 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(CidetailedReport cidetailedReport) {
        return this.cidetailedReportService.queryAll(cidetailedReport);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.cidetailedReportService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param cidetailedReport 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody CidetailedReport cidetailedReport) {
        return this.cidetailedReportService.insert(cidetailedReport);
        }

/**
 * 编辑数据
 *
 * @param cidetailedReport 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody CidetailedReport cidetailedReport) {
        return this.cidetailedReportService.update(cidetailedReport);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.cidetailedReportService.deleteById(id);
        }

        }


