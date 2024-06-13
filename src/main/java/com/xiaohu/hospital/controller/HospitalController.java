package com.xiaohu.hospital.controller;

import com.xiaohu.hospital.entity.Hospital;
import com.xiaohu.hospital.service.HospitalService;
import com.xiaohu.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Hospital)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@RestController
@RequestMapping("hospital")
public class HospitalController {
/**
 * 服务对象
 */
@Resource
private HospitalService hospitalService;

/**
 * 全查询
 *
 * @param hospital 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(Hospital hospital) {
        return this.hospitalService.queryAll(hospital);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.hospitalService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param hospital 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody Hospital hospital) {
        return this.hospitalService.insert(hospital);
        }

/**
 * 编辑数据
 *
 * @param hospital 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody Hospital hospital) {
        return this.hospitalService.update(hospital);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.hospitalService.deleteById(id);
        }

        }


