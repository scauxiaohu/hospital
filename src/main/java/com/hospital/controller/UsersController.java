package com.hospital.controller;

import com.hospital.entity.Users;
import com.hospital.service.UsersService;
import com.hospital.util.Result;
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

@GetMapping("login")
        public Result login(@RequestBody Users user) {
            return usersService.login(user);
        }
}

