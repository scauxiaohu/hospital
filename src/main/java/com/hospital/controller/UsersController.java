package com.hospital.controller;

import com.hospital.entity.Users;
import com.hospital.service.UsersService;
import com.hospital.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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
     * @param user
     * @return
     */
    @PostMapping("login")
        public Result login(@RequestBody Users user) {

        return usersService.login(user);
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

