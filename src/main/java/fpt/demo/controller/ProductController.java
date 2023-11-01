package fpt.demo.controller;

import fpt.demo.model.Product;
import fpt.demo.model.dto.CreateProductDTO;
import fpt.demo.model.dto.UpdateProductDTO;
import fpt.demo.model.dto.ViewProductDTO;
import fpt.demo.model.requestModel.CategoryIdListRequest;
import fpt.demo.model.requestModel.ProductCategoryListRequest;
import fpt.demo.model.requestModel.ProductCategoryRequest;
import fpt.demo.model.requestModel.ProductPriceRequest;
import fpt.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v0_01/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Manager functionality
    // Add product: input field: productName, price, description, importPrice, itemInStock, imagePath
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.ok("Product added successfully!");
    }

    // Manager functionality
    // Add product: input field: productName, price, description, importPrice, itemInStock, imagePath
    @PostMapping("/dto/add")
    public ResponseEntity<String> addProductDTO(@RequestBody CreateProductDTO createProductDTO){
        productService.addProductByDTO(createProductDTO);
        return ResponseEntity.ok("Product added successfully!");
    }

    // Manger functionality
    // Add list product dtos
    @PostMapping("/dto/list/add")
    public ResponseEntity<String> addListProductDTO(@RequestBody List<CreateProductDTO> createProductDTOs){
        productService.addProductByDTOs(createProductDTOs);
        return ResponseEntity.ok("Products added successfully!");
    }

    // Manager functionality
    // Get product with id
    @RequestMapping(path = "{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    // Customer functionality
    // Get product with id
    @RequestMapping(path = "/dto/{productId}")
    public ResponseEntity<ViewProductDTO> getViewProductDTOById(@PathVariable("productId") Long productId){
        return ResponseEntity.ok(productService.getViewProductDTOById(productId));
    }

    // Manager functionality
    // Get all products list
    @RequestMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Customer functionality
    // Get all products list
    @RequestMapping("/dto")
    public ResponseEntity<List<ViewProductDTO>> getAllViewProductDTOs(){
        return ResponseEntity.ok(productService.getAllViewProductDTOs());
    }

    // Manager functionality
    // Delete product with id
    @DeleteMapping(path = "/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
        //return "Product deleted!";
    }

    // Manager functionality
    // Update product
    // category is too binded
    @PutMapping(path = "/update/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("productId") Long productId){
        return ResponseEntity.ok(productService.updateProduct(product, productId));
    }

    // Manager functionality
    // Update product by dto
    // category is too binded
    @PutMapping(path = "/update/dto/{productId}")
    public ResponseEntity<Product> updateProductByDTO(@RequestBody UpdateProductDTO updateProductDTO, @PathVariable("productId") Long productId){
        return ResponseEntity.ok(productService.updateProductByDTO(updateProductDTO, productId));
    }

    //---------------------------------------------------------------------------------
    @PutMapping(path = "/add/category")
    public ResponseEntity<Product> addCategory(@RequestBody ProductCategoryRequest request){
        return ResponseEntity.ok(productService.addCategory(request.getProductId(), request.getCategoryId()));
    }

    @DeleteMapping(path = "/delete/category")
    public ResponseEntity<Product> deleteCategory(@RequestBody ProductCategoryRequest request){
        return ResponseEntity.ok(productService.removeCategory(request.getProductId(), request.getCategoryId()));
    }

    // ok
    @PostMapping(path = "/search")
    public List<Product> searchProductByName(@RequestParam("keyword") String searchString){
        return productService.findByProductNameContaining(searchString);
    }

    @PostMapping(path = "/search/category")
    public List<Product> searchProductByCategory(@RequestParam("name") String categoryName){
        return productService.findByCategoryName(categoryName);
    }

    @PostMapping(path = "/search/category/{categoryId}")
    public List<Product> searchProductByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.findByCategoryId(categoryId);
    }

    @PostMapping(path = "/search/categories")
    public List<Product> searchProductByCategory(@RequestBody CategoryIdListRequest categoryIdListRequest){
        return productService.findByCategoryIdList(categoryIdListRequest.getCategoryIdList());
    }

    @PutMapping(path = "/update/price")
    public Product updateProductPrice(@RequestBody ProductPriceRequest request){
        return productService.updateProductPrice(request.getProductId(), request.getPrice());
    }

    @PutMapping(path = "/update/price/discount")
    public Product updateProductPriceWithDiscount(@RequestBody ProductPriceRequest request){
        return productService.updateProductPrice(request.getProductId(), productService.getDiscountedPrice(request.getProductId(), request.getDiscount()));
    }

    //in this case the api call json only need to be 3
    @PutMapping(path = "/{productId}/manager/stocktoshelf")
    public Product moveItemFromStockToShelf(@PathVariable("productId") Long productId, @RequestBody int quantity){
        return productService.stockToShelf(productId, quantity);
    }

    //in this case the api call json should be "quantity":3
//    public Product moveItemFromStockToShelf(@PathVariable("productId") Long productId, @RequestBody Map<String, Integer> requestBody) {
//        int quantity = requestBody.get("quantity");
//        return productService.stockToShelf(productId, quantity);
//    }

    @PutMapping(path = "/{productId}/manager/shelftostock")
    public Product moveItemFromShelfToStock(@PathVariable("productId") Long productId, @RequestBody int quantity){
        return productService.shelfToStock(productId, quantity);
    }

//    @PutMapping(path = "/put/category")
//    public void putCategory(@RequestBody Long productId, @RequestBody Long categoryId){
//        productService.addCategory(productId, categoryId);
//    }
}
