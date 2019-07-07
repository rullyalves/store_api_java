package com.store.role.impl;

import java.util.Collection;
import com.store.exceptions.ResourceNotFoundException;
import com.store.role.IRoleDao;
import com.store.role.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public Collection<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id) {
        try {
            return roleDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A role de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(RoleDto roleDto) {
        return roleDao.save(roleDto);
    }

    @Override
    public void update(RoleDto roleDto) {
        roleDao.update(roleDto);
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

}
