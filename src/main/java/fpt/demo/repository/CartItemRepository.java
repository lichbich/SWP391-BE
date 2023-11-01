package fpt.demo.repository;

import fpt.demo.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public void deleteByIdAndCart_CartId(Long cartItemId, Long cartId);
}
