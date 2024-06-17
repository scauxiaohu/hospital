package com.hospital.controller;

import com.hospital.request.CalendarRequest;
import com.hospital.service.CalendarService;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @Resource
    private CalendarService calendarService;

    /**
     *
     * @param calendarRequest
     * @return
     */

    @PostMapping("/getCalendar")
    public Result getCalendar(@RequestBody CalendarRequest calendarRequest) {
    return  calendarService.listAppointmentCalendar(calendarRequest);
    }
}
