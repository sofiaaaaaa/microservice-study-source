package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.jpa.OrderEntity;
import com.example.demo.messagequeue.KafkaProducer;
import com.example.demo.messagequeue.OrderProducer;
import com.example.demo.service.OrderService;
import com.example.demo.vo.RequestOrder;
import com.example.demo.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-service")
public class OrderController {
    OrderService orderService;
    Environment env;
    KafkaProducer kafkaProducer;

    OrderProducer orderProducer;

    @Autowired
    public OrderController(Environment env, OrderService orderService, KafkaProducer kafkaProducer, OrderProducer orderProducer) {
        this.orderService = orderService;
        this.env = env;
        this.kafkaProducer = kafkaProducer;
        this.orderProducer = orderProducer;
    }

    @GetMapping("/health_check")
    public String status() {

        return String.format("It's working on port %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(
            @PathVariable("userId") String userId,
            @RequestBody RequestOrder order
    ) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(order, OrderDto.class);
        orderDto.setUserId(userId);
        orderDto.setOrderId( UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());
//        orderDto = orderService.createOrder(orderDto);
//        ResponseOrder responseOrder = mapper.map(orderDto, ResponseOrder.class);

        kafkaProducer.send("example-catalog-topic", orderDto);
        orderProducer.send("orders", orderDto);


        ResponseOrder responseOrder = mapper.map(orderDto, ResponseOrder.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(
            @PathVariable("userId") String userId
    ) {
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();

        orderList.forEach( v -> {
            result.add(new ModelMapper().map(v, ResponseOrder.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
