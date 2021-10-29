package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;

@Repository
public class OrderRepository {
    public List<Order> findAllOrders(){
        return DataHolder.orders;
    }

    public Order saveOrUpdate(Order order){
        DataHolder.orders.add(order);
        return order;
    }
}
