package com.hospital.controller;

import com.hospital.entity.Cireport;
import com.hospital.service.CireportService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Cireport)表控制层
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@RestController
@RequestMapping("cireport")
public class CireportController {
/**
 * 服务对象
 */
@Resource
private CireportService cireportService;

        @GetMapping("checkCireport/{userId}")
        public Result checkCireport(@PathVariable("userId") String userId) {
                return cireportService.checkCireport(userId);
        }
        }


