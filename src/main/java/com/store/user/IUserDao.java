package com.store.user;

import java.util.Collection;
import com.store.user.impl.User;
import com.store.user.impl.UserCreateDto;
import com.store.user.impl.UserDto;

public interface IUserDao {

    Collection<User> findAll();

    User findById(Long id);

    Long save(UserCreateDto UserDto);

    void update(UserDto UserDto);

    void delete(Long id);

}

