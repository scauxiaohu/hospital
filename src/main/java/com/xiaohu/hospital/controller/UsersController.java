package com.xiaohu.hospital.controller;

import com.xiaohu.hospital.entity.Users;
import com.xiaohu.hospital.service.UsersService;
import com.xiaohu.hospital.util.Result;
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
 * 全查询
 *
 * @param users 筛选条件
 * @return 查询结果
 */
@GetMapping
public Result queryAll(Users users) {
        return this.usersService.queryAll(users);
        }

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("{id}")
public Result queryById(@PathVariable("id") String id) {
        return this.usersService.queryById(id);
        }

/**
 * 新增数据
 *
 * @param users 实体
 * @return 新增结果
 */
@PostMapping
public Result add(@RequestBody Users users) {
        return this.usersService.insert(users);
        }

/**
 * 编辑数据
 *
 * @param users 实体
 * @return 编辑结果
 */
@PutMapping
public Result edit(@RequestBody Users users) {
        return this.usersService.update(users);
        }

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */
@DeleteMapping
public Result deleteById(String id) {
        return this.usersService.deleteById(id);
        }

        }


