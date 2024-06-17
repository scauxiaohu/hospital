package com.hospital.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRequest {
    private Integer hpId;
    private Integer year;
    private Integer month;
}
