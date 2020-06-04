package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.TagRepository;
import com.weekmenu.weekmenu.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getTagById(Integer id){
        return tagRepository.findById(id).get();
    }

    public List<Tag> getTagsByGroupId(Integer id){
        return tagRepository.findTagsByGroupId(id);
    }
}
