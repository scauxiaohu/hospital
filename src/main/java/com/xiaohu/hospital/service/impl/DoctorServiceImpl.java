package com.xiaohu.hospital.service.impl;

import com.xiaohu.hospital.entity.Doctor;
import com.xiaohu.hospital.mapper.DoctorMapper;
import com.xiaohu.hospital.service.DoctorService;
import com.xiaohu.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Doctor)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
@Resource
private DoctorMapper doctorMapper;

/**
 * 通过ID查询单条数据
 *
 * @param docId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer docId) {
        return Result.success(this.doctorMapper.queryById(docId));
        }

/**
 * 全查询
 *
 * @param doctor 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(Doctor doctor) {
        return Result.success(this.doctorMapper.queryAll(doctor));
        }

/**
 * 新增数据
 *
 * @param doctor 实例对象
 * @return 实例对象
 */
@Override
public Result insert(Doctor doctor) {
        this.doctorMapper.insert(doctor);
        return Result.success(doctor);
        }

/**
 * 修改数据
 *
 * @param doctor 实例对象
 * @return 实例对象
 */
@Override
public Result update(Doctor doctor) {
        this.doctorMapper.update(doctor);
        return Result.success(this.queryById(doctor.getDocId()));
        }

/**
 * 通过主键删除数据
 *
 * @param docId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer docId) {
        boolean del = this.doctorMapper.deleteById(docId) > 0;
        return Result.success(del);
        }
        }

