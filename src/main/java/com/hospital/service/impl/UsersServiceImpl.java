package com.hospital.service.impl;

import com.hospital.entity.Users;
import com.hospital.mapper.UsersMapper;
import com.hospital.service.UsersService;
import com.hospital.util.JwtUtils;
import com.hospital.util.Result;
import com.hospital.util.SendMessage;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hospital.util.Status.*;

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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录 user
     *
     * @return
     */
    public Result login(Users user) {

        List<Users> users = usersMapper.queryAll(user);
        if (users.size() == 0 || users == null) {
            Users userResult = usersMapper.queryById(user.getUserId());
            if (userResult == null) {
                return Result.error(USER_LOGIN_NOT_EXIST);
            } else {
                return Result.error(USER_LOGIN_PASSWORD_ERROR);
            }
        }
        //创建redis缓存,放token令牌

        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        String token = JwtUtils.generateJwt(map);
        // 准备一个HashMap来存储与JWT关联的额外信息到Redis
        Map<String, String> tokenInfo = new HashMap<>();
        tokenInfo.put("userId", user.getUserId()); // 存储用户ID，便于根据token查找用户
        tokenInfo.put("token", token); // 可选，也可以只存储token作为标识
        tokenInfo.put("expiration", String.valueOf(System.currentTimeMillis() + 1800000L)); // 存储JWT的过期时间戳
        // 将token作为键，关联信息作为值存入Redis
        //设计这个键的时间长度，可以根据实际情况设置，比如15分钟，30分钟，1小时，1天等等

        redisTemplate.opsForHash().putAll(token, tokenInfo);
        redisTemplate.expire(token, 1800, TimeUnit.SECONDS);
        /* return Result.success(users.get(0));*/

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", users.get(0));
        System.out.println(result);

        return Result.success(result);

    }

    @Override
    @Transactional
    public Result register(Users user, String code) {
        //TODO 验证码校验
        String codeRedis = (String) redisTemplate.opsForValue().get(user.getUserId());
        System.out.println(codeRedis);
        if (codeRedis == null) {
            return Result.error(USER_CAPTCHA_NOT_EXIST);
        }
        if (!code.equals(codeRedis)) {
            return Result.error(USER_CAPTCHA_ERROR);
        }
        Users userResult = usersMapper.queryById(user.getUserId());
        if (userResult != null) {
            return Result.error(USER_REGISTER_ALREADY_EXIST);
        }
        user.setUserType(1);
        Integer result = usersMapper.insert(user);
        if (result == 1)
            return Result.success(user);
        else return Result.error(USER_REGISTER_FAILED);
    }

    @Override
    public Result loginByCode(String userId, String code) {
        //TODO 验证码校验
        String codeRedis = (String) redisTemplate.opsForValue().get(userId);
        System.out.println(codeRedis);

        if (!code.equals(codeRedis)) {
            return Result.error(USER_CAPTCHA_ERROR);
        }

        Users user = usersMapper.queryById(userId);
        if (user == null) {
            return Result.error(USER_LOGIN_NOT_EXIST);
        }
        //创建redis缓存,放token令牌

        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        String token = JwtUtils.generateJwt(map);
        // 准备一个HashMap来存储与JWT关联的额外信息到Redis
        Map<String, String> tokenInfo = new HashMap<>();
        tokenInfo.put("userId", user.getUserId()); // 存储用户ID，便于根据token查找用户
        tokenInfo.put("token", token); // 可选，也可以只存储token作为标识
        tokenInfo.put("expiration", String.valueOf(System.currentTimeMillis() + 1800000L)); // 存储JWT的过期时间戳
        // 将token作为键，关联信息作为值存入Redis
        //设计这个键的时间长度，可以根据实际情况设置，比如15分钟，30分钟，1小时，1天等等

        redisTemplate.opsForHash().putAll(token, tokenInfo);
        redisTemplate.expire(token, 1800, TimeUnit.SECONDS);
        /* return Result.success(users.get(0));*/

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", user);
        System.out.println(result);

        return Result.success(result);
    }

    @Override
    public Result sendCode(String phone) {
        //先判断该手机号是否已经发送过验证码
        if (redisTemplate.opsForValue().get(phone) != null) {
            return Result.error(USER_CAPTCHA_SEND_TOO_FAST);
        }

        //TODO 发送验证码
        SendMessage sendMessage = new SendMessage();
        String result = sendMessage.message(phone);

        redisTemplate.opsForValue().set(phone, result, 60, TimeUnit.SECONDS);
        return Result.success(USER_CAPTCHA_SUCCESS);
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

    public Result updatePasswordProcessOne(Users users, String code) {
        List<Users> usersList = usersMapper.queryAll(users);
        if (usersList.size() == 0 || usersList == null) {
            return Result.error(USER_UPDATE_PASSWORD_NOT_EXIST);
        }
            //TODO 验证码校验
        String codeRedis = (String) redisTemplate.opsForValue().get(users.getUserId());
        System.out.println(codeRedis);
        if (codeRedis == null) {
            return Result.error(USER_CAPTCHA_NOT_EXIST);
    }
        if (!code.equals(codeRedis)) {
            return Result.error(USER_CAPTCHA_ERROR);
        }
        return Result.success(USER_CAPTCHA_SUCCESS);


    }

    public Result updatePasswordProcessTwo(Users users) {
        Integer result = usersMapper.update(users);

        if (result == 1) {
            return Result.success(USER_UPDATE_PASSWORD_SUCCESS);
        }
        else
        {
            return Result.error(USER_UPDATE_PASSWORD_FAILED);
        }
    }
}

