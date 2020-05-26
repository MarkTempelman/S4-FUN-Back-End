package com.weekmenu.weekmenu.helpers;

import com.weekmenu.weekmenu.models.User;
import com.weekmenu.weekmenu.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;

public class ControllerHelpers {
    public static User GetCurrentUser(UserService service){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.Get(name);
    }
}
