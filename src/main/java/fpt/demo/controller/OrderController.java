package fpt.demo.controller;
import fpt.demo.model.Cart;
import fpt.demo.model.Order;
import fpt.demo.model.requestModel.OrderRequest;
import fpt.demo.service.CartService;
import fpt.demo.service.OrderService;
import fpt.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fpt.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v0_01/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    public OrderController(OrderService orderService, ProductService productService, CartService cartService){
        this.orderService = orderService;
        this.cartService = cartService;
        this.productService = productService;
    }

    // Create a new order
    @PostMapping(path = "/add")
    public ResponseEntity<Order> addOrder(@RequestBody OrderRequest orderRequest) {
        Cart cart = cartService.getCartById(orderRequest.getCartId());
        Order order = Order.builder()
                .receiver(orderRequest.getReceiver())
                .address(orderRequest.getAddress())
                .phoneNum(orderRequest.getPhoneNum())
                .items(orderService.convertCartItemToOrderItem(cart.getItems()))
                .build();
        Order createdOrder = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing order
    @PutMapping("/update/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order, orderId);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an order by ID
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
//        boolean deleted = orderService.deleteOrder(id);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
