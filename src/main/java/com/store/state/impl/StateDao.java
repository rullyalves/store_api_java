package com.store.state.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.store.state.IStateDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class StateDao implements IStateDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<State> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("state_id", "id");
        aliases.put("state_name", "name");
        aliases.put("state_uf", "symbol");
     
        RowMapper<State> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(State.class);

        String sql = "SELECT state_id,state_name FROM states";

        try {
            return jdbc.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Collection<State> findByRegionId(Long regionId) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("state_id", "id");
        aliases.put("state_name", "name");
        aliases.put("state_uf", "symbol");

        RowMapper<State> rowMapper = JdbcTemplateMapperFactory
        .newInstance()
        .addAliases(aliases)
        .newRowMapper(State.class);

        String sql = "SELECT state_id,state_name,state_uf FROM states WHERE state_region_id = ?";

        try {
            Collection<State> state = jdbc.query(sql, rowMapper, regionId);
            return state;
        } catch (DataAccessException e) {
            throw e;
        }
    }


    @Override
    public State findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("state_id", "id");
        aliases.put("state_name", "name");
        aliases.put("state_uf", "symbol");

        RowMapper<State> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(State.class);
        String sql = "SELECT state_id,state_name,state_uf FROM states WHERE state_id = ?";

        try {
            State State = jdbc.queryForObject(sql, rowMapper, id);
            return State;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(Long regionId,StateDto stateDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {

            String sql = "INSERT INTO states(state_name,state_uf,state_region_id) VALUES(?,?,?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, stateDto.getName());
                ps.setString(2, String.valueOf(stateDto.getSymbol()));
                ps.setLong(3, regionId);

                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(StateDto StateDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM states WHERE state_id = ? ";

            jdbc.update(sql, id);

        } catch (DataAccessException e) {
            throw e;
        }
    }


}