package com.hospital.controller;

import com.hospital.entity.Hospital;
import com.hospital.service.HospitalService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Hospital)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@RestController
@RequestMapping("hospital")
public class HospitalController {
        /**
         * 服务对象
         */
        @Resource
        private HospitalService hospitalService;
        /**
         * 查询所有医院信息
         * @return 医院信息
         */
        @GetMapping("hospitalList")
        public Result hospitalList() {
                return hospitalService.hospitalList();
        }

}