package fpt.demo.service.servceImp;

import fpt.demo.model.CartItem;
import fpt.demo.model.Order;
import fpt.demo.model.OrderItem;
import fpt.demo.repository.OrderRepository;
import fpt.demo.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {


    @Autowired
    public OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(false);
        return orderRepository.save(order);
    }

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
        return null;
    }

    @Override
    public List<OrderItem> convertCartItemToOrderItem(List<CartItem> cartItems) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem: cartItems){
            OrderItem orderItem = OrderItem.builder()
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .build();
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
