package com.hospital.service;

import com.hospital.entity.Hospital;
import com.hospital.util.Result;

/**
 * (Hospital)表服务接口
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
public interface HospitalService {

        /**
         * 通过ID查询单条数据
         *
         * @param hpId 主键
         * @return 实例对象
         */
        Result queryById(Integer hpId);

        /**
         * 全查询
         *
         * @param hospital 筛选条件
         * @return 查询结果
         */
        Result queryAll(Hospital hospital);

        /**
         * 新增数据
         *
         * @param hospital 实例对象
         * @return 实例对象
         */
        Result insert(Hospital hospital);

        /**
         * 修改数据
         *
         * @param hospital 实例对象
         * @return 实例对象
         */
        Result update(Hospital hospital);

        /**
         * 通过主键删除数据
         *
         * @param hpId 主键
         * @return 是否成功
         */
        Result deleteById(Integer hpId);

        }

