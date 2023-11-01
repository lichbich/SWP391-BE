package fpt.demo.service;

import fpt.demo.model.CartItem;
import fpt.demo.model.Order;
import fpt.demo.model.OrderItem;
import fpt.demo.model.dto.CreateOrderDTO;
import fpt.demo.model.requestModel.CartItemListRequest;
import fpt.demo.model.requestModel.OrderItemListRequest;

import java.util.List;
import java.util.Set;

public interface OrderService {
    public CreateOrderDTO createOrderFromCart(CartItemListRequest cartItemListRequest);
    public Order addOrder(CreateOrderDTO orderDTO);
    public Order getOrderById(Long orderId);
    public List<Order> getAllOrders();
    public void deleteOrder(Long orderId);
    public Order updateOrder(Order order, Long orderId);

    public Order putOrderItem(Long orderId, OrderItem orderItem); //not advisable to use
    public OrderItem getOrderItemById(Long orderId, Long orderItemId);
    public Set<CartItem> convertCartItemIdsToCartItems(CartItemListRequest cartItemListRequest);
    public Set<OrderItem> convertCartItemsToOrderItems(Set<CartItem> cartItems);
    public double getTotalPrice(OrderItemListRequest orderItemListRequest);
}
