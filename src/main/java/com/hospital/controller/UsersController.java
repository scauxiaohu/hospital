package com.hospital.controller;

import com.hospital.entity.Users;
import com.hospital.service.UsersService;
import com.hospital.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Users)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@RestController
@RequestMapping("users")
public class UsersController {
/**
 * 服务对象
 */
@Resource
private UsersService usersService;

    /**
     * 登录
     * @param userId
     * @param password
     * @return
     */
    @GetMapping("login")
        public Result login(@RequestParam("userId") String userId,@RequestParam("password") String password) {
        System.out.println(userId+password);
            return usersService.login(userId,password);
        }


    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestBody Users user) {
        return usersService.register(user);
    }
}

