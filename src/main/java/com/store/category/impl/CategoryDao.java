package com.store.category.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.store.category.ICategoryDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao implements ICategoryDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<Category> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("category_id", "id");
        aliases.put("category_name", "name");

        RowMapper<Category> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Category.class);

        String sql = "SELECT category_id,category_name FROM categories";

        try {

            return jdbc.query(sql, rowMapper);

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Category findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("category_id", "id");
        aliases.put("category_name", "name");

        RowMapper<Category> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Category.class);

        String sql = "SELECT category_id,category_name FROM categories WHERE category_id = ?";

        try {
            Category category = jdbc.queryForObject(sql, rowMapper, id);

            return category;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(CategoryDto categoryDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {
            String sql = "INSERT INTO categories(category_name) VALUES(?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, categoryDto.getName());
                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(CategoryDto categoryDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM categories WHERE category_id = ? ";

            jdbc.update(sql, id);

        } catch (DataAccessException e) {
            throw e;
        }
    }

}