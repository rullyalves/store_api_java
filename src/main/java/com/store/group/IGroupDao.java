package com.store.group;

import java.util.Collection;

import com.store.group.impl.Group;
import com.store.group.impl.GroupDto;


public interface IGroupDao {

    Collection<Group> findAll();

    Group findById(Long id);

    Long save(GroupDto groupDto);

    void update(GroupDto groupDto);

    void delete(Long id);

}

