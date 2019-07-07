package com.store.user.impl;

import java.util.Collection;
import com.store.user.IUserDao;
import com.store.user.IUserService;
import com.store.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public Collection<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        try {
            return userDao.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("A o usuário de id " + id + " não foi encontrada", e.getCause());
        }
    }

    @Override
    public Long save(UserCreateDto UserDto) {
        return userDao.save(UserDto);
    }

    @Override
    public void update(UserDto UserDto) {
        userDao.update(UserDto);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
