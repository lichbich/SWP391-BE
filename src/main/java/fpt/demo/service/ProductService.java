package fpt.demo.service;

import fpt.demo.model.Category;
import fpt.demo.model.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);
    public Product getProductById(Long productId);
    public List<Product> getAllProducts();
    public void deleteProduct(Long productId);
    public Product updateProduct(Product product, Long productId);

    public void addCategory(Long productId, Long categoryId);
    public void addCategoryList(Long productId, List<Long> categoryIdList);

}
