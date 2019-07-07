package com.store.role.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.store.role.IRoleDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao implements IRoleDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<Role> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("role_id", "id");
        aliases.put("role_name", "name");
        aliases.put("role_description", "description");

        RowMapper<Role> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Role.class);

        String sql = "SELECT role_id,role_name,role_description FROM roles";

        try {
            return jdbc.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Role findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("role_id", "id");
        aliases.put("role_name", "name");
        aliases.put("role_description", "description");

        RowMapper<Role> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Role.class);

        String sql = "SELECT role_id,role_name,role_description FROM roles WHERE role_id = ?";

        try {
            Role role = jdbc.queryForObject(sql, rowMapper, id);

            return role;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(RoleDto roleDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {
            String sql = "INSERT INTO roles(role_name,role_description) VALUES(?,?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, roleDto.getName());
                ps.setString(2, roleDto.getDescription());
                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(RoleDto roleDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM roles WHERE role_id = ? ";

            jdbc.update(sql, id);

        } catch (DataAccessException e) {
            throw e;
        }
    }

}