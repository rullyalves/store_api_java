package com.store.order.impl;

import com.store.order.IOrderDao;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
@Repository
public class OrderDao implements IOrderDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Collection<Order> findAll() {
        try{
            String sql = "SELECT order_id, order_created, order_updated FROM orders";
            RowMapper<Order> rowMapperOrders = JdbcTemplateMapperFactory
                    .newInstance().newRowMapper(Order.class);
            return jdbc.query(sql,rowMapperOrders);
        }
        catch(DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Order findById(Long id) {
        try {
            String sql = "SELECT order_id, order_created, order_updated FROM orders WHERE id = ?";
            RowMapper<Order> rowMapperOrder = JdbcTemplateMapperFactory
                .newInstance().newRowMapper(Order.class);
            return jdbc.queryForObject(sql, rowMapperOrder, id);

        }
        catch (DataAccessException e) {
            throw e;
        }
    }
    //podipa
    @Override
    public Collection<Order> findOrdersByCustumerId(Long customerId) {
        try {
            //podipa
            String sql = "SELECT order_id, order_created, order_update FROM orders WHERE order_user_profile_id = ? ";
            RowMapper<Order> rowMapperOrders = JdbcTemplateMapperFactory
                    .newInstance().newRowMapper(Order.class);
            return jdbc.query(sql, rowMapperOrders, customerId);
        } catch (DataAccessException e){
            throw e;
      }
    }

    @Override
    public Long save(Long custumerId) {

        final KeyHolder keyHolder = new GeneratedKeyHolder();

        try {


            String sql = "INSERT INTO orders (order_user_profile_id) VALUES (?)";
            RowMapper<Order> rowMapperOrder = JdbcTemplateMapperFactory
                    .newInstance().newRowMapper(Order.class);
            //return jdbc.update(sql, rowMapperOrder, custumerId);
          jdbc.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//DONT FORGET THE FUCKING GEN
                    ps.setLong(1, custumerId);
                    return ps;

            },keyHolder);

          return (long) keyHolder.getKey();
        }
        catch (DataAccessException e ) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM orders WHERE order_id = ?";
            jdbc.update(sql, id);
        }
        catch (DataAccessException e) {
            throw e;
        }
    }
/*
    @Override
    public void update(Long id, OrderDto orderDto) {

    }
*/

}

