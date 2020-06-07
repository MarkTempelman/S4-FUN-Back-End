package com.weekmenu.weekmenu.interfaces;

import com.weekmenu.weekmenu.models.Dish;
import com.weekmenu.weekmenu.models.Weekmenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface WeekmenuRepository extends JpaRepository<Weekmenu, Integer> {
    List<Weekmenu> findWeekmenusByGroupIdAndStartDate(Integer groupId, Date startDate);

    @Query("SELECT COUNT (w) FROM Weekmenu w WHERE w.groupId = ?1 AND w.startDate =?2")
    Integer howManyWeekmenusWithGroupIdAndStartDate(Integer groupId, Date startDate);
}
