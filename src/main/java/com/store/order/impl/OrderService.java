package com.store.order.impl;

import com.store.order.IOrderDao;
import com.store.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDao iOrderDao;

    @Override
    public Collection<Order> findAll() {
        return iOrderDao.findAll();
    }

    @Override
    public Order findById(Long id) {
        return iOrderDao.findById(id);
    }

    @Override
    public Collection<Order> findOrdersByCustumerId(Long customerId) {
        return iOrderDao.findOrdersByCustumerId(customerId);
    }

    @Override
    public Long save(Long custumerId) {
        return iOrderDao.save(custumerId);
    }

    @Override
    public void delete(Long id) {
        iOrderDao.delete(id);
    }
}
