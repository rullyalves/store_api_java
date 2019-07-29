package com.store.permission;

import com.store.permission.impl.Permission;
import com.store.permission.impl.PermissionDto;

import java.util.Collection;

public interface IPermissionService {


    Long save(PermissionDto permission);

    Collection<Permission> findAll();

    Permission findById(Long id);

    void delete(Long id);

    void update(Long id, PermissionDto permission);

}
