package com.store.permission;

import com.store.permission.impl.Permission;
import com.store.permission.impl.PermissionDto;

import java.util.Collection;

public interface IPermissionDao{

    Collection<Permission> findAll();

    Permission findById(Long id);

    Long save(PermissionDto permission);

    void update(Long id, PermissionDto permission);

    void delete(Long id);
}
