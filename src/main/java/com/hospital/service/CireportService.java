package com.hospital.service;

import com.hospital.entity.Cireport;
import com.hospital.util.Result;

/**
 * (Cireport)表服务接口
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
public interface CireportService {

        /**
         * 通过ID查询单条数据
         *
         * @param cirId 主键
         * @return 实例对象
         */
        Result queryById(Integer cirId);

        /**
         * 全查询
         *
         * @param cireport 筛选条件
         * @return 查询结果
         */
        Result queryAll(Cireport cireport);

        /**
         * 新增数据
         *
         * @param cireport 实例对象
         * @return 实例对象
         */
        Result insert(Cireport cireport);

        /**
         * 修改数据
         *
         * @param cireport 实例对象
         * @return 实例对象
         */
        Result update(Cireport cireport);

        /**
         * 通过主键删除数据
         *
         * @param cirId 主键
         * @return 是否成功
         */
        Result deleteById(Integer cirId);

    Result checkCireport(String userId);
}

