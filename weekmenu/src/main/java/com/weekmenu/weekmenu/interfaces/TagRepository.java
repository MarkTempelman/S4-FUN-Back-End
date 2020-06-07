package com.weekmenu.weekmenu.interfaces;

import com.weekmenu.weekmenu.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    List<Tag> findTagsByGroupId(Integer groupId);

    boolean existsTagByTagName (String tagName);
}
