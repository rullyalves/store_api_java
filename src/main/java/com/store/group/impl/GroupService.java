package com.store.group.impl;

import java.util.Collection;
import com.store.exceptions.ResourceNotFoundException;
import com.store.group.IGroupDao;
import com.store.group.IGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService implements IGroupService {

    @Autowired
    IGroupDao groupDao;

    @Override
    public Collection<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public Group findById(Long id) {
        try {
            return groupDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("O grupo de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(GroupDto groupDto) {
        return groupDao.save(groupDto);
    }

    @Override
    public void update(GroupDto groupDto) {
        groupDao.update(groupDto);
    }

    @Override
    public void delete(Long id) {
        groupDao.delete(id);
    }

}
