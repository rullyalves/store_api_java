package com.store.orderitem;

import com.store.orderitem.impl.OrderItem;

import java.util.Collection;

public interface IOrderItemService {

    Collection<OrderItem> findAll();
    OrderItem findById(Long id);
    OrderItem findOrderItemsByOrderId(Long id);
    Long save(OrderItemDto orderItemDto);
    void update(Long id, OrderItemDto orderItemDto);
    void delete(Long id);

}
