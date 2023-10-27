package fpt.demo.service;

import fpt.demo.model.Cart;
import fpt.demo.model.CartItem;

import java.util.List;

public interface CartService {
    public Cart addCart(Cart cart);
    public Cart getCartById(Long cartId);
    public List<Cart> getAllCarts();
    public void deleteCart(Long cartId);
    public Cart updateCart(Cart cart, Long cartId);

    public Cart putCartItem(Long cartId, CartItem cartItem);
    public void deleteCartItem(Long cartId, CartItem cartItem);
    public CartItem getCartItemById(Long cartId, Long cartItemId);
}
