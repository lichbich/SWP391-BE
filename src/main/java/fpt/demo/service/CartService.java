package fpt.demo.service;

import fpt.demo.model.Cart;
import fpt.demo.model.CartItem;
import fpt.demo.model.requestModel.CartItemListRequest;
import fpt.demo.model.requestModel.CartRequest;

import java.util.List;

public interface CartService {
    public Cart createCart();
    public CartItem createCartItem(CartRequest itemRequest);
    public Cart addCart(Cart cart);
    public Cart getCartById(Long cartId);
    public List<Cart> getAllCarts();
    public String deleteCart(Long cartId);
    public Cart updateCart(Cart cart, Long cartId);

    public Cart putCartItem(CartRequest itemRequest);
    public void deleteCartItem(Long cartId, Long cartItemId);
    public CartItem getCartItemById(Long cartId, Long cartItemId);
    public double getTotalPrice(CartItemListRequest cartItemListRequest);
}
