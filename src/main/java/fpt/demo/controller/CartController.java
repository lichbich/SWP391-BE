package fpt.demo.controller;

import fpt.demo.model.Cart;
import fpt.demo.model.CartItem;
import fpt.demo.model.Product;
import fpt.demo.model.requestModel.CartItemListRequest;
import fpt.demo.model.requestModel.CartItemRequest;
import fpt.demo.model.requestModel.CartRequest;
import fpt.demo.service.CartService;
import fpt.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //status: finished
    //Initialize cart
    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(){
        return ResponseEntity.ok(cartService.createCart());
    }

    //status: unfinished
    //add cart, not advisable to use
    @PostMapping("/add")
    public String addCart(@RequestBody Cart cart){
        //need to save cart back to cartItem
//        for(CartItem item: cart.getItems()){
//            item.setCart(cart);
//        }
        cartService.addCart(cart);
        //to test if added successfully
        String str = "";
        for(CartItem item: cart.getItems()){
            str += item.getId();
        }
        return str;//"Cart added successfully!"; //str;
    }

    //status: finished
    //get cart by id
    @RequestMapping(path = "{cartId}")
    public Cart getCartById(@PathVariable("cartId") Long cartId){
        return cartService.getCartById(cartId);
    }

    //status: finished
    //get all carts list
    @RequestMapping("")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    //status: unfinished
    @PutMapping(path = "/item/add")
    public Cart putCartItem(@RequestBody CartRequest itemRequest){
        return cartService.putCartItem(itemRequest);
    }

    //unfinished: partly worked
    @DeleteMapping(path = "/item/delete")
    public void deleteCartItem(@RequestBody CartItemRequest request){
        //CartItem item = cartService.getCartItemById(request.getCartId(), request.getCartItemId());
        cartService.deleteCartItem(request.getCartId(), request.getCartItemId());
    }

    //status: finished
    @GetMapping(path = "/item")
    public CartItem getCartItemById(@RequestBody CartItemRequest request){
        return cartService.getCartItemById(request.getCartId(), request.getCartItemId());
    }

    //status: finished
    @PostMapping(path = "/total")
    public double getTotalPrice(@RequestBody CartItemListRequest request){
        return cartService.getTotalPrice(request);
    }


    //status: unfinished
    //delete cart, not advisable to use
    @DeleteMapping(path = "/delete/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable("cartId") Long cartId){
        return ResponseEntity.ok(cartService.deleteCart(cartId));
    }

    //status: unfinished
    //update cart, not advisable to use
    @PutMapping(path = "/update/{cartId}")
    public Cart updateCart(@RequestBody Cart cart, @PathVariable("cartId") Long cartId){
        return cartService.updateCart(cart, cartId);
    }



}
