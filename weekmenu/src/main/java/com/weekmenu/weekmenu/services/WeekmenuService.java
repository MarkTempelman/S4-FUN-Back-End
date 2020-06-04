package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.WeekmenuRepository;
import com.weekmenu.weekmenu.models.Weekmenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WeekmenuService {
    private final WeekmenuRepository repo;

    @Autowired
    public WeekmenuService(WeekmenuRepository repository){
        repo = repository;
    }

    public List<Weekmenu> getCurrentWeekmenuByGroupId(Integer id){
        LocalDate monday = LocalDate.now();
        monday = monday.with(DayOfWeek.MONDAY);
        Date date = java.sql.Date.valueOf(monday);
        return this.repo.findWeekmenusByGroupIdAndStartDate(id, date);
    }
}
