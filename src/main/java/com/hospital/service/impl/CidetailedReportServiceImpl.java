package com.hospital.service.impl;

import com.hospital.service.CidetailedReportService;
import com.hospital.entity.CidetailedReport;
import com.hospital.mapper.CidetailedReportMapper;
import com.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (CidetailedReport)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Service("cidetailedReportService")
public class CidetailedReportServiceImpl implements CidetailedReportService {
@Resource
private CidetailedReportMapper cidetailedReportMapper;

/**
 * 通过ID查询单条数据
 *
 * @param cidrId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer cidrId) {
        return Result.success(this.cidetailedReportMapper.queryById(cidrId));
        }

/**
 * 全查询
 *
 * @param cidetailedReport 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(CidetailedReport cidetailedReport) {
        return Result.success(this.cidetailedReportMapper.queryAll(cidetailedReport));
        }

/**
 * 新增数据
 *
 * @param cidetailedReport 实例对象
 * @return 实例对象
 */
@Override
public Result insert(CidetailedReport cidetailedReport) {
        this.cidetailedReportMapper.insert(cidetailedReport);
        return Result.success(cidetailedReport);
        }

/**
 * 修改数据
 *
 * @param cidetailedReport 实例对象
 * @return 实例对象
 */
@Override
public Result update(CidetailedReport cidetailedReport) {
        this.cidetailedReportMapper.update(cidetailedReport);
        return Result.success(this.queryById(cidetailedReport.getCidrId()));
        }

/**
 * 通过主键删除数据
 *
 * @param cidrId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer cidrId) {
        boolean del = this.cidetailedReportMapper.deleteById(cidrId) > 0;
        return Result.success(del);
        }
        }

