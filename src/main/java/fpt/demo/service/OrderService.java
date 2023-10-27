package fpt.demo.service;

import fpt.demo.model.CartItem;
import fpt.demo.model.Order;
import fpt.demo.model.OrderItem;

import java.util.List;

public interface OrderService {
    public Order addOrder(Order order);
    public Order getOrderById(Long orderId);
    public List<Order> getAllOrders();
    public void deleteOrder(Long orderId);
    public Order updateOrder(Order order, Long orderId);

    public Order putOrderItem(Long orderId, OrderItem orderItem);
    public OrderItem getOrderItemById(Long orderId, Long orderItemId);
    public List<OrderItem> convertCartItemToOrderItem(List<CartItem> cartItems);
}
