package com.store.user.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.store.user.IUserDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements IUserDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<User> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("user_id", "id");
        aliases.put("user_email", "email");
        aliases.put("user_password", "password");

        RowMapper<User> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(User.class);

        String sql = "SELECT user_id,user_email,user_password FROM users";

        try {
            return jdbc.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public User findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("user_id", "id");
        aliases.put("user_email", "email");
        aliases.put("user_password", "password");

        RowMapper<User> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(User.class);

        String sql = "SELECT user_id,user_email,user_password FROM users WHERE user_id = ?";

        try {
            User User = jdbc.queryForObject(sql, rowMapper, id);
            return User;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(UserCreateDto userDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {
            String sql = "INSERT INTO users(user_email,user_password) VALUES(?,?)";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, userDto.getEmail());
                ps.setString(2, userDto.getPassword());
                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(UserDto userDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ? ";
            jdbc.update(sql, id);
        } catch (DataAccessException e) {
            throw e;
        }
    }

}