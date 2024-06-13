package com.hospital.service.impl;

import com.hospital.entity.OverallResult;
import com.hospital.mapper.OverallResultMapper;
import com.hospital.service.OverallResultService;
import com.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (OverallResult)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Service("overallResultService")
public class OverallResultServiceImpl implements OverallResultService {
@Resource
private OverallResultMapper overallResultMapper;

/**
 * 通过ID查询单条数据
 *
 * @param orId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer orId) {
        return Result.success(this.overallResultMapper.queryById(orId));
        }

/**
 * 全查询
 *
 * @param overallResult 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(OverallResult overallResult) {
        return Result.success(this.overallResultMapper.queryAll(overallResult));
        }

/**
 * 新增数据
 *
 * @param overallResult 实例对象
 * @return 实例对象
 */
@Override
public Result insert(OverallResult overallResult) {
        this.overallResultMapper.insert(overallResult);
        return Result.success(overallResult);
        }

/**
 * 修改数据
 *
 * @param overallResult 实例对象
 * @return 实例对象
 */
@Override
public Result update(OverallResult overallResult) {
        this.overallResultMapper.update(overallResult);
        return Result.success(this.queryById(overallResult.getOrId()));
        }

/**
 * 通过主键删除数据
 *
 * @param orId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer orId) {
        boolean del = this.overallResultMapper.deleteById(orId) > 0;
        return Result.success(del);
        }
        }

