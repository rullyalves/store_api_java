package com.store.city.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.store.city.ICityDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CityDao implements ICityDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<City> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("city_id", "id");
        aliases.put("city_name", "name");

        RowMapper<City> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(City.class);

        String sql = "SELECT city_id,city_name FROM cities";

        try {
            return jdbc.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Collection<City> findByStateId(Long stateId) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("city_id", "id");
        aliases.put("city_name", "name");

        RowMapper<City> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(City.class);

        String sql = "SELECT city_id,city_name FROM cities WHERE city_state_id = ?";

        try {
            Collection<City> city = jdbc.query(sql, rowMapper, stateId);
            return city;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public City findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("city_id", "id");
        aliases.put("city_name", "name");

        RowMapper<City> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(City.class);
        String sql = "SELECT city_id,city_name FROM cities WHERE city_id = ?";

        try {
            City city = jdbc.queryForObject(sql, rowMapper, id);
            return city;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(Long stateId, CityDto cityDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {

            String sql = "INSERT INTO cities(city_name,city_state_id) VALUES(?,?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, cityDto.getName());
                ps.setLong(2, stateId);

                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(CityDto CityDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM cities WHERE city_id = ? ";

            jdbc.update(sql, id);

        } catch (DataAccessException e) {
            throw e;
        }
    }

}