package com.hospital.service.impl;

import com.hospital.entity.CheckItem;
import com.hospital.mapper.CheckItemMapper;
import com.hospital.service.CheckItemService;
import com.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (CheckItem)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Service("checkItemService")
public class CheckItemServiceImpl implements CheckItemService {
@Resource
private CheckItemMapper checkItemMapper;

/**
 * 通过ID查询单条数据
 *
 * @param ciId 主键
 * @return 实例对象
 */
@Override
public Result queryById(Integer ciId) {
        return Result.success(this.checkItemMapper.queryById(ciId));
        }

/**
 * 全查询
 *
 * @param checkItem 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(CheckItem checkItem) {
        return Result.success(this.checkItemMapper.queryAll(checkItem));
        }

/**
 * 新增数据
 *
 * @param checkItem 实例对象
 * @return 实例对象
 */
@Override
public Result insert(CheckItem checkItem) {
        this.checkItemMapper.insert(checkItem);
        return Result.success(checkItem);
        }

/**
 * 修改数据
 *
 * @param checkItem 实例对象
 * @return 实例对象
 */
@Override
public Result update(CheckItem checkItem) {
        this.checkItemMapper.update(checkItem);
        return Result.success(this.queryById(checkItem.getCiId()));
        }

/**
 * 通过主键删除数据
 *
 * @param ciId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(Integer ciId) {
        boolean del = this.checkItemMapper.deleteById(ciId) > 0;
        return Result.success(del);
        }
        }

