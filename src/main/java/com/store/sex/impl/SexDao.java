package com.store.sex.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.store.sex.ISexDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SexDao implements ISexDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<Sex> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("sex_id", "id");
        aliases.put("sex_name", "name");

        RowMapper<Sex> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Sex.class);

        String sql = "SELECT sex_id,sex_name FROM sexes";

        try {
            return jdbc.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Sex findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("sex_id", "id");
        aliases.put("sex_name", "name");

        RowMapper<Sex> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Sex.class);

        String sql = "SELECT sex_id,sex_name FROM sexes WHERE sex_id = ?";

        try {
            Sex Sex = jdbc.queryForObject(sql, rowMapper, id);

            return Sex;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(SexDto SexDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {
            String sql = "INSERT INTO sexes(sex_name) VALUES(?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, SexDto.getName());
                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(SexDto SexDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM sexes WHERE sex_id = ? ";

            jdbc.update(sql, id);

        } catch (DataAccessException e) {
            throw e;
        }
    }

}