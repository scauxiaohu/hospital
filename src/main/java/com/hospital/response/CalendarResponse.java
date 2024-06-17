package com.hospital.response;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponse {
    private String ymd;         //预约日期
    private Integer total;      //最大预约人数
    private Integer existing;   //现有预约人数
    private Integer remainder;  //剩余预约人数

    public CalendarResponse(String ymd) {
        this.ymd = ymd;
    }
}
