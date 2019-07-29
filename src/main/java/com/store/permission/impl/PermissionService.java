package com.store.permission.impl;

import com.store.permission.IPermissionDao;
import com.store.permission.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class PermissionService implements IPermissionService {
    @Autowired
    IPermissionDao permissionDao;

    @Override
    public Long save(PermissionDto permission) {
        return permissionDao.save(permission);
    }

    @Override
    public Collection<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public Permission findById(Long id) {
        return permissionDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        permissionDao.delete(id);
    }

    @Override
    public void update(Long id, PermissionDto permission) {
        permissionDao.update(id, permission);
    }
}
