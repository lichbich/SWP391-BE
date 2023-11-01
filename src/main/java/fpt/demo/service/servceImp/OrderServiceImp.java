package fpt.demo.service.servceImp;

import fpt.demo.model.Cart;
import fpt.demo.model.CartItem;
import fpt.demo.model.Order;
import fpt.demo.model.OrderItem;
import fpt.demo.model.dto.CreateOrderDTO;
import fpt.demo.model.requestModel.CartItemListRequest;
import fpt.demo.model.requestModel.OrderItemListRequest;
import fpt.demo.repository.CartRepository;
import fpt.demo.repository.OrderRepository;
import fpt.demo.service.CartService;
import fpt.demo.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImp implements OrderService {


    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public CartService cartService;

    @Override
    public CreateOrderDTO createOrderFromCart(CartItemListRequest cartItemListRequest){
        Set<CartItem> cartItems = convertCartItemIdsToCartItems(cartItemListRequest);
        Set<OrderItem> orderItems = convertCartItemsToOrderItems(cartItems);
        CreateOrderDTO orderDTO = CreateOrderDTO.builder()
                .orderItems(orderItems)
                .build();
        return orderDTO;
    }

    @Override
    public Order addOrder(CreateOrderDTO orderDTO) {
        Order order = Order.builder()
                .receiver(orderDTO.getReceiver())
                .address(orderDTO.getAddress())
                .phoneNum(orderDTO.getPhoneNum())
                .items(orderDTO.getOrderItems())
                .createdAt(LocalDateTime.now())
                .status(false)
                .build();
        return orderRepository.save(order);
    }

//    @Override
//    public Order addOrder(Order order) {
//        order.setCreatedAt(LocalDateTime.now());
//        order.setStatus(false);
//        return orderRepository.save(order);
//    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll() ;
    }

    @Override
    public void deleteOrder(Long orderId) {
         orderRepository.deleteById(orderId);
    }

    @Override
    public Order updateOrder(Order order, Long orderId) {
        order.setOrderId(orderId);
        return orderRepository.save(order);
    }

    @Override
    public Order putOrderItem(Long orderId, OrderItem orderItem) {
        return null;
    }

    @Override
    public OrderItem getOrderItemById(Long orderId, Long orderItemId) {
        // Loop through items of order and match id
        Order order = orderRepository.findById(orderId).get();
        for(OrderItem item : order.getItems()){
            if(item.getId() == orderItemId){
                return item;
            }
        }
        return null;
    }

    @Override
    public Set<CartItem> convertCartItemIdsToCartItems(CartItemListRequest cartItemListRequest){
        Set<CartItem> cartItems = new HashSet<>();
        Long cartId = cartItemListRequest.getCartId();
        for(Long item : cartItemListRequest.getCartItemIdList()){
            cartItems.add(cartService.getCartItemById(cartId, item));
        }
        return cartItems;
    }

    @Override
    public Set<OrderItem> convertCartItemsToOrderItems(Set<CartItem> cartItems) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem: cartItems){
            OrderItem orderItem = OrderItem.builder()
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .build();
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    @Override
    public double getTotalPrice(OrderItemListRequest orderItemListRequest) {
        //check cartId
        double totalPrice = 0;
        for (Long item : orderItemListRequest.getOrderItemIdList()){
            OrderItem orderItem = getOrderItemById(orderItemListRequest.getOrderId(), item);
            totalPrice += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        return totalPrice;
    }
}
