package com.weekmenu.weekmenu.helpers;

import com.weekmenu.weekmenu.models.User;
import com.weekmenu.weekmenu.models.Weekmenu;
import com.weekmenu.weekmenu.models.WeekmenuDish;
import com.weekmenu.weekmenu.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

public class ControllerHelpers {
    public static User GetCurrentUser(UserService service){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.Get(name);
    }

    public static Integer GetCurrentGroupId(UserService service){
        User user = GetCurrentUser(service);
        return user.getGroupId();
    }

    public static Set<WeekmenuDish> sortWeekmenuDishesByDay(Set<WeekmenuDish> unsorted){
        Comparator<WeekmenuDish> compareByDay = Comparator.comparing(WeekmenuDish::getDay);
        List<WeekmenuDish> unsortedList = new ArrayList<WeekmenuDish>(unsorted);
        unsortedList.sort(compareByDay);
        Set<WeekmenuDish> sortedSet = new LinkedHashSet<>(unsortedList);
        return sortedSet;
    }
}
