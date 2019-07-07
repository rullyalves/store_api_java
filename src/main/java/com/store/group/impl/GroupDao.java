package com.store.group.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.store.group.IGroupDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDao implements IGroupDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<Group> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("group_id", "id");
        aliases.put("group_name", "name");
        aliases.put("group_description", "description");

        RowMapper<Group> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Group.class);

        String sql = "SELECT group_id,group_name,group_description FROM groups";

        try {
            return jdbc.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Group findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("group_id", "id");
        aliases.put("group_name", "name");
        aliases.put("group_description", "description");

        RowMapper<Group> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Group.class);

        String sql = "SELECT group_id,group_name,group_description FROM groups WHERE group_id = ?";

        try {
            Group Group = jdbc.queryForObject(sql, rowMapper, id);

            return Group;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(GroupDto GroupDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {
            String sql = "INSERT INTO groups(group_name,group_description) VALUES(?,?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, GroupDto.getName());
                ps.setString(2, GroupDto.getDescription());
                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(GroupDto GroupDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM groups WHERE group_id = ? ";
            jdbc.update(sql, id);
        } catch (DataAccessException e) {
            throw e;
        }
    }

}