package com.xiaohu.hospital.service;

import com.xiaohu.hospital.entity.Doctor;
import com.xiaohu.hospital.util.Result;

/**
 * (Doctor)表服务接口
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
public interface DoctorService {

        /**
         * 通过ID查询单条数据
         *
         * @param docId 主键
         * @return 实例对象
         */
        Result queryById(Integer docId);

        /**
         * 全查询
         *
         * @param doctor 筛选条件
         * @return 查询结果
         */
        Result queryAll(Doctor doctor);

        /**
         * 新增数据
         *
         * @param doctor 实例对象
         * @return 实例对象
         */
        Result insert(Doctor doctor);

        /**
         * 修改数据
         *
         * @param doctor 实例对象
         * @return 实例对象
         */
        Result update(Doctor doctor);

        /**
         * 通过主键删除数据
         *
         * @param docId 主键
         * @return 是否成功
         */
        Result deleteById(Integer docId);

        }

