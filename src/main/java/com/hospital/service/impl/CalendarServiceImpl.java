package com.hospital.service.impl;

import com.hospital.mapper.HospitalMapper;
import com.hospital.mapper.OrdersMapper;
import com.hospital.request.CalendarRequest;
import com.hospital.response.CalendarResponse;
import com.hospital.service.CalendarService;
import com.hospital.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("CalendarService")
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    /**
     * 获取日历信息
     * @param calendarRequest
     * @return
     */
    public Result listAppointmentCalendar(CalendarRequest calendarRequest)
    {
      List<CalendarResponse> calendarResponseList = getCalendarResultList(calendarRequest.getHpId());
      List<CalendarResponse> calendarBaseResponseList = getCalendarBaseResultList(calendarRequest.getYear(), calendarRequest.getMonth());

      for(CalendarResponse calendarBase : calendarBaseResponseList)
      {
          for(CalendarResponse  calendar : calendarResponseList)
          {
              if(calendar.getYmd().equals(calendarBase.getYmd()))
              {
                  calendarBase.setTotal(calendar.getTotal());
                  calendarBase.setExisting(calendar.getExisting());
                  calendarBase.setRemainder(calendar.getRemainder());

              }
          }
      }
        return  Result.success(calendarBaseResponseList);
    }

    private List<CalendarResponse> getCalendarBaseResultList(Integer year, Integer month) {
        // 基础日历
        List<CalendarResponse> calendarResponseList = new ArrayList<>();
        // 获取该月的第一天
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        Integer day=firstDayOfMonth.getDayOfWeek().getValue();

        // 获取该月的总天数
        YearMonth yearMonth = YearMonth.of(year, month);
        Integer daysInMonth = yearMonth.lengthOfMonth();


        if(day==7)day=0;
        for (int i = 0; i < day; i++) {
            CalendarResponse calendarResponse = new CalendarResponse();
            calendarResponseList.add(calendarResponse);
        }

        for (int i = 1; i <= daysInMonth; i++) {
            String m = month < 10? "0" + month : month+"";
            String d = i < 10? "0" + i : i+"";
            String ymd = year + "-" + m + "-" + d;
            calendarResponseList.add(new CalendarResponse(ymd));
        }
        return calendarResponseList;

    }

        private List<CalendarResponse> getCalendarResultList(Integer hpId)
    {
       //获得当天日期
        LocalDate today = LocalDate.now();
    //获得今天往后30天的日期列表
        List<LocalDate> dateList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dateList.add(today.plusDays(i));
        }

        List<CalendarResponse> calendarResponseList =ordersMapper.getCalendarResultList(hpId,dateList);
        String arr[]=hospitalMapper.queryById(hpId).getRule().split(",");
        for(CalendarResponse calendarResponse : calendarResponseList)
        {

            LocalDate date = LocalDate.parse(calendarResponse.getYmd());
            Integer day=date.getDayOfWeek().getValue();
            if(day==7)day=0;
            Integer total=Integer.parseInt(arr[day]);
            Integer remainder=total-calendarResponse.getExisting();
            calendarResponse.setTotal(total);
            calendarResponse.setRemainder(remainder);

        }

        return calendarResponseList;
    }
}
