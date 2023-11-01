package fpt.demo.controller;
import fpt.demo.model.Cart;
import fpt.demo.model.CartItem;
import fpt.demo.model.Order;
import fpt.demo.model.OrderItem;
import fpt.demo.model.dto.CreateOrderDTO;
import fpt.demo.model.requestModel.*;
import fpt.demo.service.CartService;
import fpt.demo.service.OrderService;
import fpt.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fpt.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    //status: unfinished
    // Create a new order
//    @PostMapping(path = "/add")
//    public ResponseEntity<Order> addOrder(@RequestBody OrderRequest orderRequest) {
//        Cart cart = cartService.getCartById(orderRequest.getCartId());
//        List<CartItem> cartItemList = new ArrayList<>();
//        for(Long itemId : orderRequest.getCartItemIdList()){
//            cartItemList.add(cartService.getCartItemById(orderRequest.getCartId(), itemId));
//        }
//        List<OrderItem> orderItemList = orderService.convertCartItemToOrderItem(cartItemList);
//        Order order = Order.builder()
//                .receiver(orderRequest.getReceiver())
//                .address(orderRequest.getAddress())
//                .phoneNum(orderRequest.getPhoneNum())
//                //.items(orderService.convertCartItemToOrderItem(cart.getItems())) //#1: put all items in cart to order
//        //        .items(orderItemList) //#2: put selected items from cart to order
//                .build();
//        Order createdOrder = orderService.addOrder(order);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
//    }

    @PostMapping(path = "/dto/create")
    public ResponseEntity<CreateOrderDTO> createCreateOrderDTO(@RequestBody CartItemListRequest cartItemListRequest) {
        CreateOrderDTO orderDTO = orderService.createOrderFromCart(cartItemListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    //add receiver, address, phonenum
    @PostMapping(path = "/add")
    public ResponseEntity<Order> addOrder(@RequestBody CreateOrderDTO orderDTO){
        return ResponseEntity.ok(orderService.addOrder(orderDTO));
    }

    //status: unfinished
    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    //status: unfinished
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

    //status: unfinished
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

    //status: unfinished
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

    //status: unfinished
    @GetMapping(path = "/item")
    public OrderItem getOrderItemById(@RequestBody OrderItemRequest request){
        return orderService.getOrderItemById(request.getOrderId(), request.getOrderItemId());
    }

    //status: unfinished
    @PostMapping(path = "/total")
    public double getTotalPrice(@RequestBody OrderItemListRequest request){
        return orderService.getTotalPrice(request);
    }

}
