package com.store.orderitem.impl;

import com.store.order.impl.Order;
import com.store.orderitem.IOrderItemDao;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class OrderItemDao implements IOrderItemDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<OrderItem> findAll() {
        try {
            String sql = "SELECT order_item_id, order_item_amount FROM order_items";
            RowMapper<OrderItem> rowMapper = JdbcTemplateMapperFactory
                    .newInstance().newRowMapper(OrderItem.class);
            return jdbcTemplate.query(sql, rowMapper);
        } catch(DataAccessException e){
            throw e;
        }
    }

    @Override
    public OrderItem findById(Long id) {
        try {
           String sql = "SELECT order_item_id, order_item_amount FROM order_items WHERE order_item_id = ?";
           RowMapper<OrderItem> rowMapper = JdbcTemplateMapperFactory.newInstance().newRowMapper(OrderItem.class);
           return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Collection<OrderItem> findOrderItemsByOrderId(Long orderId) {
        try{
            String sql = "SELECT order_item_id, order_item_amount FROM order_items WHERE order_item_order_id = ?";
            RowMapper<OrderItem> rowMapper = JdbcTemplateMapperFactory.newInstance().newRowMapper(OrderItem.class);
            return jdbcTemplate.query(sql, rowMapper, orderId);
        } catch(DataAccessException e){
            throw e;
        }

    }

    @Override
    public Long save(OrderItemDto orderItemDto) {
        return null;
    }

    @Override
    public void update(Long id, OrderItemDto orderItemDto) {

    }

    @Override
    public void delete(Long id) {

    }
}
