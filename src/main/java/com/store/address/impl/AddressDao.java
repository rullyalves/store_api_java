package com.store.address.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.store.address.IAddressDao;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao implements IAddressDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<Address> findAll() {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("address_id", "id");
        aliases.put("address_zipcode", "zipCode");
        aliases.put("address_street", "street");

        RowMapper<Address> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Address.class);

        String sql = "SELECT address_id,address_zipcode,adress_street FROM address";

        try {
            return jdbc.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Collection<Address> findByCustomerId(Long customerId) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("address_id", "id");
        aliases.put("address_zipcode", "zipCode");
        aliases.put("address_street", "street");

        RowMapper<Address> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Address.class);

        String sql = "SELECT address_id,address_zipcode,address_street FROM address WHERE address_user_profile_id = ?";

        try {
            Collection<Address> Address = jdbc.query(sql, rowMapper, customerId);
            return Address;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Collection<Address> findByCityId(Long cityId) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("address_id", "id");
        aliases.put("address_zipcode", "zipCode");
        aliases.put("address_street", "street");

        RowMapper<Address> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Address.class);

        String sql = "SELECT address_id,address_zipcode,address_street FROM address WHERE address_city_id = ?";

        try {
            Collection<Address> Address = jdbc.query(sql, rowMapper, cityId);
            return Address;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Address findById(Long id) {
        Map<String, String> aliases = new HashMap<String, String>();
        aliases.put("address_id", "id");
        aliases.put("address_zipcode", "zipCode");
        aliases.put("address_street", "street");

        RowMapper<Address> rowMapper = JdbcTemplateMapperFactory.newInstance().addAliases(aliases)
                .newRowMapper(Address.class);
        String sql = "SELECT address_id,address_zipcode,address_street FROM address WHERE address_id = ?";

        try {
            Address Address = jdbc.queryForObject(sql, rowMapper, id);
            return Address;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Long save(Long customerId, AddressCreateDto addressDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();

        try {

            String sql = "INSERT INTO address(address_zipcode,address_street,address_city_id,address_user_profile_id) VALUES(?,?,?,?) ";

            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, addressDto.getZipCode());
                ps.setString(2, addressDto.getStreet());
                ps.setLong(3, addressDto.getCityId());
                ps.setLong(4, customerId);
            
                // MUDAR ESSA PORRA DEPOIS

                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void update(Long customerId, AddressDto addressDto) {
        try {

        } catch (DataAccessException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM address WHERE address_id = ? ";

            jdbc.update(sql, id);

        } catch (DataAccessException e) {
            throw e;
        }
    }

}