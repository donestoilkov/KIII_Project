package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class InMemoryOrderRepository {
    public List<Order> findAllOrders() {
        return DataHolder.orders;
    }

    public void saveOrUpdate(Order order) {
        DataHolder.orders.add(order);
    }
}
