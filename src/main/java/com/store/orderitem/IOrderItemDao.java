package com.store.orderitem;

import com.store.orderitem.impl.OrderItem;

import java.util.Collection;

public interface IOrderItemDao {
    Collection<OrderItem> findAll();
    OrderItem findById(Long id);
    Collection<OrderItem> findOrderItemsByOrderId(Long orderId);
    Long save(OrderItemDto orderItemDto);
    void update(Long id, OrderItemDto orderItemDto);
    void delete(Long id);

}
