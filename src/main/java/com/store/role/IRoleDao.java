package com.store.role;

import java.util.Collection;

import com.store.role.impl.Role;
import com.store.role.impl.RoleDto;


public interface IRoleDao {

    Collection<Role> findAll();

    Role findById(Long id);

    Long save(RoleDto roleDto);

    void update(RoleDto roleDto);

    void delete(Long id);

}

