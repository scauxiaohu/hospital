package com.xiaohu.hospital.service.impl;

import com.xiaohu.hospital.entity.Cireport;
import com.xiaohu.hospital.mapper.CireportMapper;
import com.xiaohu.hospital.service.CireportService;
import com.xiaohu.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Cireport)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Service("cireportService")
public class CireportServiceImpl implements CireportService {
@Resource
private CireportMapper cireportMapper;

/**
 * 通过ID查询单条数据
 *
 * @param cirId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer cirId) {
        return Result.success(this.cireportMapper.queryById(cirId));
        }

/**
 * 全查询
 *
 * @param cireport 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(Cireport cireport) {
        return Result.success(this.cireportMapper.queryAll(cireport));
        }

/**
 * 新增数据
 *
 * @param cireport 实例对象
 * @return 实例对象
 */
@Override
public Result insert(Cireport cireport) {
        this.cireportMapper.insert(cireport);
        return Result.success(cireport);
        }

/**
 * 修改数据
 *
 * @param cireport 实例对象
 * @return 实例对象
 */
@Override
public Result update(Cireport cireport) {
        this.cireportMapper.update(cireport);
        return Result.success(this.queryById(cireport.getCirId()));
        }

/**
 * 通过主键删除数据
 *
 * @param cirId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer cirId) {
        boolean del = this.cireportMapper.deleteById(cirId) > 0;
        return Result.success(del);
        }
        }

