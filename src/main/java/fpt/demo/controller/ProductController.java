package fpt.demo.controller;

import fpt.demo.model.Product;
import fpt.demo.model.requestModel.ProductCategoryListRequest;
import fpt.demo.model.requestModel.ProductCategoryRequest;
import fpt.demo.service.ProductService;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v0_01/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/hello")
    public String hello_world(){
        return "Hello world";
    }

    //add product
    @PostMapping("/add")
    public String addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return "Product added successfully!";
    }

    //get product by id
    @RequestMapping(path = "{productId}")
    public Product getProductById(@PathVariable("productId") Long productId){
        return productService.getProductById(productId);
    }

    //get all products list
    @RequestMapping("")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping(path = "/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
        //return "Product deleted!";
    }

    @PutMapping(path = "/update/{productId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("productId") Long productId){
        return productService.updateProduct(product, productId);
    }

    @PutMapping(path = "/put/category")
    public void putCategory(@RequestBody ProductCategoryRequest request){
        productService.addCategory(request.getProductId(), request.getCategoryId());
    }

    @PutMapping(path = "/put/category/list")
    public void putCategoriesList(@RequestBody ProductCategoryListRequest request) {
        productService.addCategoryList(request.getProductId(), request.getCategoryIdList());
    }



//    @PutMapping(path = "/put/category")
//    public void putCategory(@RequestBody Long productId, @RequestBody Long categoryId){
//        productService.addCategory(productId, categoryId);
//    }
}
