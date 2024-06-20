package com.hospital;

import com.hospital.entity.Users;
import com.hospital.request.CalendarRequest;
import com.hospital.service.CalendarService;
import com.hospital.service.CireportService;
import com.hospital.service.UsersService;
import com.hospital.service.impl.CireportServiceImpl;
import com.hospital.service.impl.UsersServiceImpl;
import com.hospital.util.SendMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;

@SpringBootTest
class HospitalApplicationTests {
@Autowired
private CalendarService calendarService;
@Resource
private UsersService userService;
    @Test
    void contextLoads() {
        LocalDate date = LocalDate.now();
        //假如日期为05月01日，则dayOfMonth为5
        date = date.withDayOfMonth(05);
        System.out.println(date); // 假设这是您的日期对象
        int dayOfMonth = date.getDayOfMonth(); // 提取日期部分
        String dayStr = String.valueOf(dayOfMonth); // 转换为字符串，个位数时自然不会有前导零
        System.out.println(dayStr);
    }
    @Resource
    private CireportService cireportService;
    @Test
    void contextLoads1() {
        UsersServiceImpl usersService = new UsersServiceImpl();
        Users users = new Users();
        usersService.login(users);
    }
}
