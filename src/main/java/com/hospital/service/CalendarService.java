package com.hospital.service;

import com.hospital.request.CalendarRequest;
import com.hospital.response.CalendarResponse;
import com.hospital.util.Result;

import java.util.List;

public interface CalendarService {
    public Result listAppointmentCalendar(CalendarRequest calendarRequest);
}
