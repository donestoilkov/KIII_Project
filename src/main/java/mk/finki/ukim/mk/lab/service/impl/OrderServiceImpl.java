package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String balloonColor,String balloonSize, String clientName, String address) {
        Long id = null;
        Random rd = new Random();
        id = rd.nextLong();
        Order order = new Order(balloonColor,balloonSize,clientName,address,id);
        orderRepository.saveOrUpdate(order);
        return order;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAllOrders();
    }
}
