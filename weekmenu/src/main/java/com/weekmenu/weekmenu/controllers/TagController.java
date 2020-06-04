package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.helpers.ControllerHelpers;
import com.weekmenu.weekmenu.models.Tag;
import com.weekmenu.weekmenu.models.User;
import com.weekmenu.weekmenu.services.TagService;
import com.weekmenu.weekmenu.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TagController {
    private final TagService tagService;
    private final UserService userService;

    public TagController(TagService tagService, UserService userService) {
        this.tagService = tagService;
        this.userService = userService;
    }

    @GetMapping("/admin/tags")
    public List<Tag> getTags(){
        User user = ControllerHelpers.GetCurrentUser(userService);
        return tagService.getTagsByGroupId(user.getGroupId());
    }
}
