package com.store.permission.impl;

import com.store.permission.IPermissionDao;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

public class PermissionDao implements IPermissionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Collection<Permission> findAll() {
        try {
            String sql = "SELECT permission_id, permission_group_id, permission_role_id FROM permissions";

            RowMapper<Permission> permissionRowMapper = JdbcTemplateMapperFactory
                    .newInstance()
                    .newRowMapper(Permission.class);

        return jdbcTemplate.query(sql,permissionRowMapper);
        }
        catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Permission findById(Long id) {
        try {
            String sql = "SELECT permission_id, permission_group_id, permission_role_id FROM permissions WHERE permission_id = ?";

            RowMapper<Permission> permissionRowMapper = JdbcTemplateMapperFactory
                    .newInstance()
                    .newRowMapper(Permission.class);

        return jdbcTemplate.queryForObject(sql,permissionRowMapper);
        }
        catch (DataAccessException e){
            throw e;
        }
    }


    @Override
    public Long save(PermissionDto permission) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();
        try {
            String sql = "INSERT INTO permissions(permission_group_id, permission_role_id) VALUES (?,?)";

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,permission.getGroupId());
            ps.setLong(2,permission.getRoleId());

             return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();
        }
        catch (DataAccessException e) {
            throw e;
        }
    }


    @Override
    public void update(Long id, PermissionDto permission) {

    }


    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM permissions WHERE permission_id = ?";
            jdbcTemplate.update(sql,id);
        }
        catch (DataAccessException e) {
            throw e;
        }

    }

}
