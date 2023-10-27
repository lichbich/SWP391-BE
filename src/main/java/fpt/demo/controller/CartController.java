package fpt.demo.controller;

import fpt.demo.model.Cart;
import fpt.demo.model.CartItem;
import fpt.demo.model.Product;
import fpt.demo.model.requestModel.CartItemDeleteRequest;
import fpt.demo.model.requestModel.CartItemRequest;
import fpt.demo.service.CartService;
import fpt.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v0_01/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    //add cart, not advisable to use
    @PostMapping("/add")
    public String addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return "Cart added successfully!";
    }

    //get cart by id
    @RequestMapping(path = "{cartId}")
    public Cart getCartById(@PathVariable("cartId") Long cartId){
        return cartService.getCartById(cartId);
    }

    //get all carts list
    @RequestMapping("")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    //delete cart, not advisable to use
    @DeleteMapping(path = "/delete/{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId){
        cartService.deleteCart(cartId);
        //return "Category deleted!";
    }

    //update cart, not advisable to use
    @PutMapping(path = "/update/{cartId}")
    public Cart updateCart(@RequestBody Cart cart, @PathVariable("cartId") Long cartId){
        return cartService.updateCart(cart, cartId);
    }

    @PutMapping(path = "/item/add")
    public Cart putCartItem(@RequestBody CartItemRequest itemRequest){
        Product product = productService.getProductById(itemRequest.getProductId());
        CartItem item = CartItem.builder()
                .cart(cartService.getCartById(itemRequest.getCartId())) //to be inspected
                .product(product)
                .quantity(itemRequest.getQuantity())
                .build();
        return cartService.putCartItem(itemRequest.getCartId(), item);
    }

    @DeleteMapping(path = "/item/delete")
    public void deleteCartItem(@RequestBody CartItemDeleteRequest request){
        CartItem item = cartService.getCartItemById(request.getCartId(), request.getCartItemId());
        cartService.deleteCartItem(request.getCartId(), item);
    }



}
