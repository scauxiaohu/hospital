package com.hospital.controller;

import com.hospital.config.RedisConfig;
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

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("login")
        public Result login (@RequestBody Users user) {

        return usersService.login(user);
        }

    @PostMapping("loginByCode")
    public Result loginByCode (@RequestParam String userId, @RequestParam String code) {

        return usersService.loginByCode(userId,code);
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")


    public Result register(@RequestBody Users user,@RequestParam("code") String code) {
        System.out.println(code);
        System.out.println(user);
        return usersService.register(user,code);
    }
    //发送验证码
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @GetMapping("sendCode")
    public Result sendCode(@RequestParam("phone") String phone,@RequestParam("type") Integer type) throws Exception {
        System.out.println(type);
        return usersService.sendCode(phone,type);
    }

    /**
     * 修改密码发送验证码
     * @param Users
     * @param code
     * @return
     */
    @PostMapping("updatePasswordOne")
    public Result updatePasswordProcessOne(@RequestBody Users Users,@RequestParam("code") String code) {
        return usersService.updatePasswordProcessOne(Users,code);
    }
    /**
     * 修改密码
     * @param Users
     * @return
     */
     @PostMapping("updatePasswordTwo")
    public Result updatePasswordProcessTwo(@RequestBody Users Users) {
         return usersService.updatePasswordProcessTwo(Users);
     }


}

