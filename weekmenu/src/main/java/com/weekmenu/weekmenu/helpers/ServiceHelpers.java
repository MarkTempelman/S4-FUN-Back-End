package com.weekmenu.weekmenu.helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class ServiceHelpers {
    public static Date getMondayFromLocalDate(LocalDate localDate){
        return java.sql.Date.valueOf(localDate.with(DayOfWeek.MONDAY));
    }
}
