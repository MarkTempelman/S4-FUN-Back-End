package com.weekmenu.weekmenu.services;

import com.weekmenu.weekmenu.interfaces.WeekmenuRepository;
import com.weekmenu.weekmenu.models.Weekmenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeekmenuService {
    private final WeekmenuRepository repo;

    public WeekmenuService(WeekmenuRepository repository){
        repo = repository;
    }

    @Query("select w from weekmenu w where w.group_id = ?1")
    public List<Weekmenu> getWeekmenuByGroupId(Integer id){
        return this.repo.findWeekmenusByGroupId(id);
    }
}
