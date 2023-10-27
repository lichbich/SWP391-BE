package fpt.demo.service.servceImp;

import fpt.demo.model.Cart;
import fpt.demo.model.CartItem;
import fpt.demo.repository.CartRepository;
import fpt.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public Cart updateCart(Cart cart, Long cartId) {
        cart.setCartId(cartId);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart putCartItem(Long cartId, CartItem cartItem) {
        Cart cart = cartRepository.findById(cartId).get();

        CartItem existingItem = cart.getItems().stream()
                .filter(i -> i.getProduct().equals(cartItem.getProduct()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Item exists, update quantity
            existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
        } else {
            // New item, add to cart
            cartItem.setCart(cart);
            cart.getItems().add(cartItem);
        }

        cartRepository.save(cart);
        return cart;
    }

    @Override
    @Transactional
    public void deleteCartItem(Long cartId, CartItem cartItem) {

        CartItem item = getCartItemById(cartId, cartItem.getId());
        Cart cart = item.getCart();
//        Cart cart = cartRepository.findById(cartId).get();
        cart.getItems().remove(item);
        cartRepository.save(cart);

    }

    @Override
    public CartItem getCartItemById(Long cartId, Long cartItemId) {
        // Loop through items of cart and match id
        Cart cart = cartRepository.findById(cartId).get();
        for(CartItem item : cart.getItems()){
            if(item.getId() == cartItemId){
                return item;
            }
        }
        return null;
    }

}
