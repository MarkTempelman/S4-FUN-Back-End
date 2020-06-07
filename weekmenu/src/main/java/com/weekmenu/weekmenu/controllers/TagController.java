package com.weekmenu.weekmenu.controllers;

import com.weekmenu.weekmenu.helpers.ControllerHelpers;
import com.weekmenu.weekmenu.models.Tag;
import com.weekmenu.weekmenu.models.User;
import com.weekmenu.weekmenu.services.TagService;
import com.weekmenu.weekmenu.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return tagService.getTagsByGroupId(ControllerHelpers.GetCurrentGroupId(userService));
    }

    @PostMapping("/admin/tags/create")
    public ResponseEntity createTag(@RequestBody Tag tag){
        if(!tagService.doesTagExist(tag.getTagName())){
            tag.setGroupId(ControllerHelpers.GetCurrentGroupId(userService));
            tagService.saveTag(tag);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
