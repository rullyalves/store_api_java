package com.store.region.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.store.region.IRegionDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RegionDao implements IRegionDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<Region> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("region_id", "id");
        aliases.put("region_name", "name");
     
        RowMapper<Region> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Region.class);

        String sql = "SELECT region_id,region_name FROM regions";

        try {
            return jdbc.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Region findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("region_id", "id");
        aliases.put("region_name", "name");

        RowMapper<Region> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Region.class);

        String sql = "SELECT region_id,region_name FROM regions WHERE region_id = ?";

        try {
            Region region = jdbc.queryForObject(sql, rowMapper, id);

            return region;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(RegionDto RegionDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {
            String sql = "INSERT INTO regions(region_name) VALUES(?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, RegionDto.getName());
                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(RegionDto RegionDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM regions WHERE region_id = ? ";

            jdbc.update(sql, id);

        } catch (DataAccessException e) {
            throw e;
        }
    }

}