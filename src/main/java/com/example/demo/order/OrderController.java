package com.example.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> getAllOrders(){
        return ResponseEntity.ok(orderRepository
                .findAll()
                .stream().toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id){
        return ResponseEntity.ok(orderRepository.findById(UUID.fromString(id)).get());
    }

    @PostMapping()
    public ResponseEntity createOrder() {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setTotal(19.99);
        order.setId(UUID.randomUUID());
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }
}
