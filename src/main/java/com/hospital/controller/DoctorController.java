package com.hospital.controller;

import com.hospital.entity.Doctor;
import com.hospital.service.DoctorService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Doctor)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@RestController
@RequestMapping("doctor")
public class DoctorController {
/**
 * 服务对象
 */
@Resource
private DoctorService doctorService;

/**
 * 全查询
 *
 * @param doctor 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(Doctor doctor) {
        return this.doctorService.queryAll(doctor);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") Integer id) {
        return this.doctorService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param doctor 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody Doctor doctor) {
        return this.doctorService.insert(doctor);
        }

/**
 * 编辑数据
 *
 * @param doctor 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody Doctor doctor) {
        return this.doctorService.update(doctor);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(Integer id) {
        return this.doctorService.deleteById(id);
        }

        }


