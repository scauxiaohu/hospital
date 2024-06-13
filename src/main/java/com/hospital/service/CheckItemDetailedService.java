package com.hospital.service;

import com.hospital.entity.CheckItemDetailed;
import com.hospital.util.Result;

/**
 * (CheckItemDetailed)表服务接口
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
public interface CheckItemDetailedService {

        /**
         * 通过ID查询单条数据
         *
         * @param cdId 主键
         * @return 实例对象
         */
        Result queryById(Integer cdId);

        /**
         * 全查询
         *
         * @param checkItemDetailed 筛选条件
         * @return 查询结果
         */
        Result queryAll(CheckItemDetailed checkItemDetailed);

        /**
         * 新增数据
         *
         * @param checkItemDetailed 实例对象
         * @return 实例对象
         */
        Result insert(CheckItemDetailed checkItemDetailed);

        /**
         * 修改数据
         *
         * @param checkItemDetailed 实例对象
         * @return 实例对象
         */
        Result update(CheckItemDetailed checkItemDetailed);

        /**
         * 通过主键删除数据
         *
         * @param cdId 主键
         * @return 是否成功
         */
        Result deleteById(Integer cdId);

        }

