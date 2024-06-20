package com.hospital.service;

import com.hospital.entity.Users;
import com.hospital.util.Result;

import javax.servlet.http.HttpSession;

/**
 * (Users)表服务接口
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
public interface UsersService {

        /**
         * 通过ID查询单条数据
         *
         * @param userId 主键
         * @return 实例对象
         */
        Result queryById(String userId);

        /**
         * 全查询
         *
         * @param users 筛选条件
         * @return 查询结果
         */
        Result queryAll(Users users);

        /**
         * 新增数据
         *
         * @param users 实例对象
         * @return 实例对象
         */
        Result insert(Users users);

        /**
         * 修改数据
         *
         * @param users 实例对象
         * @return 实例对象
         */
        Result update(Users users);

        /**
         * 通过主键删除数据
         *
         * @param userId 主键
         * @return 是否成功
         */
        Result deleteById(String userId);

    Result login(Users user);

        Result register(Users user,String code);

        Result sendCode(String phone);

    Result loginByCode(String userId, String code);
}

