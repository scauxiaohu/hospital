package com.hospital.service;

import com.hospital.entity.CidetailedReport;
import com.hospital.util.Result;

/**
 * (CidetailedReport)表服务接口
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
public interface CidetailedReportService {

        /**
         * 通过ID查询单条数据
         *
         * @param cidrId 主键
         * @return 实例对象
         */
        Result queryById(Integer cidrId);

        /**
         * 全查询
         *
         * @param cidetailedReport 筛选条件
         * @return 查询结果
         */
        Result queryAll(CidetailedReport cidetailedReport);

        /**
         * 新增数据
         *
         * @param cidetailedReport 实例对象
         * @return 实例对象
         */
        Result insert(CidetailedReport cidetailedReport);

        /**
         * 修改数据
         *
         * @param cidetailedReport 实例对象
         * @return 实例对象
         */
        Result update(CidetailedReport cidetailedReport);

        /**
         * 通过主键删除数据
         *
         * @param cidrId 主键
         * @return 是否成功
         */
        Result deleteById(Integer cidrId);

        }

