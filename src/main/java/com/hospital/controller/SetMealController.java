package com.hospital.controller;

import com.hospital.entity.SetMeal;
import com.hospital.service.SetMealService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

        /**
         * 根据类型获取套餐
         * @param type
         * @return
         */
        @GetMapping("getSetMealsByType")
        public Result getSetMealsByType(@RequestParam("type")Integer type) {

                return setMealService.getSetMealsByType(type);
        }


}