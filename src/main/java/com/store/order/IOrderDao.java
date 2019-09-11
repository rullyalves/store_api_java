package com.store.order;

import com.store.order.impl.Order;

import java.util.Collection;

public interface IOrderDao {

    Collection<Order> findAll();
    Order findById(Long id);
    Collection<Order> findOrdersByCustumerId(Long customerId);
    Long save(Long custumerId);
    void delete(Long id);
   // void update(Long id);

}