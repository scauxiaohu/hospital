package com.hospital.controller;

import com.hospital.entity.SetMeal;
import com.hospital.service.SetMealService;
import com.hospital.util.JwtUtils;
import com.hospital.util.Result;
import io.jsonwebtoken.Claims;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.hospital.util.Status.TOKEN_PARSE_FAILED;
import static com.hospital.util.Status.USER_GET_FAILED;

/**
 * (SetMeal)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@RestController
@RequestMapping("setMeal")
public class SetMealController {
        /**
         * 服务对象
         */
        @Resource
        private SetMealService setMealService;
        @Resource
        private RedisTemplate<String, Object> redisTemplate;
        /**
         * 根据类型获取套餐
         * @param type
         * @return
         */
        @GetMapping("getSetMealsByType")
        public Result getSetMealsByType(@RequestParam("type")Integer type,@RequestHeader("Authorization") String token) {

//尝试从Redis中获取用户ID
                String userId = (String) redisTemplate.opsForHash().get(token, "userId");
                if (userId != null)
                        return setMealService.getSetMealsByType(type, userId);
                        else
                                return Result.error(USER_GET_FAILED);
        }

        }


