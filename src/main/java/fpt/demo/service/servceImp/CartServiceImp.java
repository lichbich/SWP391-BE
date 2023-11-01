package fpt.demo.service.servceImp;

import fpt.demo.model.Cart;
import fpt.demo.model.CartItem;
import fpt.demo.model.Product;
import fpt.demo.model.requestModel.CartItemListRequest;
import fpt.demo.model.requestModel.CartRequest;
import fpt.demo.repository.CartItemRepository;
import fpt.demo.repository.CartRepository;
import fpt.demo.repository.ProductRepository;
import fpt.demo.service.CartService;
import fpt.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    // Initiallize right after creating an user
    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    @Override
    public CartItem createCartItem(CartRequest itemRequest){
        Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + itemRequest.getProductId() + " not found"));
        Cart cart = cartRepository.findById(itemRequest.getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart with id " + itemRequest.getCartId() + " not found"));
        CartItem cartItem = CartItem.builder()
                .product(product)
                .quantity(itemRequest.getQuantity())
                //.cart(cart)
                .build();
        return cartItemRepository.save(cartItem);
    }

    //currently failed
    @Override
    public Cart addCart(Cart cart) {
        Cart existingCart = createCart();
        Set<CartItem> cartItems = cart.getItems();
        existingCart.setItems(cart.getItems());
//        cartRepository.save(cart);
        cartRepository.save(existingCart);
//        for(CartItem item : cartItems){
//            item.setCart(existingCart);
//            cartItemRepository.save(item);
//        }
//        cartItemRepository.saveAll(cartItems);
        return existingCart;
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
    @Transactional
    public Cart putCartItem(CartRequest itemRequest) {
        Cart cart = cartRepository.findById(itemRequest.getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        CartItem cartItem = createCartItem(itemRequest);
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
//            cartItemRepository.save(cartItem);
            cart.getItems().add(cartItem);
        }
        return cartRepository.save(cart);
    }

    @Override
    @Modifying
    @Transactional
    public void deleteCartItem(Long cartId, Long cartItemId) {
//        // Fetch the cart
//        Cart cart = cartRepository.findById(cartId).get();
//
//        // Find the cart item to remove
//        CartItem itemToRemove = null;
//        for (CartItem item : cart.getItems()) {
//            if (item.getId() == cartItemId) {
//                itemToRemove = item;
//                break;
//            }
//        }
//
//        // Remove the item if found
//        if (itemToRemove != null) {
//            cart.getItems().remove(itemToRemove);
//            // Save the updated cart
//            cartRepository.save(cart);
//        }

        //alternative 2: currently workable
        cartItemRepository.deleteByIdAndCart_CartId(cartItemId, cartId);


//        // Get cart item by id
//        CartItem itemToRemove = getCartItemById(cartId, cartItemId);
//        // Get cart
//        Cart cart = itemToRemove.getCart();
////        Cart cart = cartRepository.findById(cartId).get();
//        // Remove item
//        cart.getItems().remove(itemToRemove);
//        // Save cart
//        cartRepository.save(cart);
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

    @Override
    public double getTotalPrice(CartItemListRequest cartItemListRequest) {
        //check cartId
        double totalPrice = 0;
        for (Long item : cartItemListRequest.getCartItemIdList()){
            CartItem cartItem = getCartItemById(cartItemListRequest.getCartId(), item);
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }

    //currently fail
    @Override
    public String deleteCart(Long cartId) {
        Cart cart = getCartById(cartId);
//        for(CartItem item : cart.getItems()){
//            cartItemRepository.delete(item);
//        }
        cart.getItems().clear();
        cartRepository.delete(cart);
        return "Cart with id " + cartId + " deleted successfully.";
    }

    //currently fail
    @Override
    public Cart updateCart(Cart cart, Long cartId) {
//        Cart currentCart = cartRepository.findById(cartId).get();
//        for(CartItem item: currentCart.getItems()){
//            deleteCartItem(cartId, item);
//        }
        cart.setCartId(cartId);
        List<CartItem> items = new ArrayList<>();
        for(CartItem item: cart.getItems()){
            item.setCart(cart);
            items.add(item);
        }
//        cart.setItems(items);
        return cartRepository.save(cart);
    }



}
