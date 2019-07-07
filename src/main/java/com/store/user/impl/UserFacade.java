package com.store.user.impl;

import com.store.user.IUserFacade;
import com.store.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFacade implements IUserFacade {

    @Autowired
    private IUserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(UserCreateDto UserCreateDto) {
        try {
            // separando em uma DTO de categoria
            UserCreateDto userDto = new UserCreateDto();

            Long userId = userService.save(userDto);

            return userId;

        } catch (Exception e) {
            throw e;
        }
    }

}