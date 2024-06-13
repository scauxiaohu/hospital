package com.hospital.service.impl;

import com.hospital.entity.Users;
import com.hospital.mapper.UsersMapper;
import com.hospital.service.UsersService;
import com.hospital.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
@Resource
private UsersMapper usersMapper;

       public Result login(Users user)
        {
           List<Users> users=usersMapper.queryAll(user);
           if(users.size()==0||users==null)
           {
                   Users userResult=usersMapper.queryById(user.getUserId());
                   if(userResult==null)
                   {
                       return Result.error("用户不存在");
                   }
                   else
                   {
                       return Result.error("密码错误");
                   }
           }
                return Result.success(users.get(0));

           }



/**
 * 通过ID查询单条数据
 *
 * @param userId 主键
 * @return 实例对象
 */
@Override
public Result queryById(String userId) {
        return Result.success(this.usersMapper.queryById(userId));
        }

/**
 * 全查询
 *
 * @param users 筛选条件
 * @return 查询结果
 */
@Override
public Result queryAll(Users users) {
        return Result.success(this.usersMapper.queryAll(users));
        }

/**
 * 新增数据
 *
 * @param users 实例对象
 * @return 实例对象
 */
@Override
public Result insert(Users users) {
        this.usersMapper.insert(users);
        return Result.success(users);
        }

/**
 * 修改数据
 *
 * @param users 实例对象
 * @return 实例对象
 */
@Override
public Result update(Users users) {
        this.usersMapper.update(users);
        return Result.success(this.queryById(users.getUserId()));
        }

/**
 * 通过主键删除数据
 *
 * @param userId 主键
 * @return 是否成功
 */
@Override
public Result deleteById(String userId) {
        boolean del = this.usersMapper.deleteById(userId) > 0;
        return Result.success(del);
        }
        }
