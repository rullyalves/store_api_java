package com.store.subcategory.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.store.subcategory.ISubcategoryDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SubcategoryDao implements ISubcategoryDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<Subcategory> findByCategoryId(Long categoryId) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("subcategory_id", "id");
        aliases.put("subcategory_name", "name");

        RowMapper<Subcategory> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Subcategory.class);

        String sql = "SELECT subcategory_id,subcategory_name FROM subcategories WHERE subcategory_category_id = ?";

        try {

            return jdbc.query(sql, rowMapper, categoryId);

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Collection<Subcategory> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("subcategory_id", "id");
        aliases.put("subcategory_name", "name");

        RowMapper<Subcategory> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Subcategory.class);

        String sql = "SELECT subcategory_id,subcategory_name FROM subcategories";

        try {

            return jdbc.query(sql, rowMapper);

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Subcategory findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("subcategory_id", "id");
        aliases.put("subcategory_name", "name");

        RowMapper<Subcategory> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Subcategory.class);

        String sql = "SELECT subcategory_id,subcategory_name FROM subcategories WHERE subcategory_id = ?";

        try {
            Subcategory category = jdbc.queryForObject(sql, rowMapper, id);

            return category;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(Long categoryId, SubcategoryDto categoryDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {
            String sql = "INSERT INTO subcategories(subcategory_name,subcategory_category_id) VALUES(?,?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, categoryDto.getName());
                ps.setLong(2, categoryId);
                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(SubcategoryDto categoryDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM subcategories WHERE subcategory_id = ? ";

            jdbc.update(sql, id);

        } catch (DataAccessException e) {
            throw e;
        }
    }

}