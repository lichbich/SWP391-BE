package fpt.demo.service;

import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.model.dto.CreateProductDTO;
import fpt.demo.model.dto.UpdateProductDTO;
import fpt.demo.model.dto.ViewProductDTO;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);
    public Product addProductByDTO(CreateProductDTO createProductDTO);
    public List<Product> addProductByDTOs(List<CreateProductDTO> createProductDTOs);
    public Product getProductById(Long productId);
    public ViewProductDTO getViewProductDTOById(Long productId);
    public List<Product> getAllProducts();
    public List<ViewProductDTO> getAllViewProductDTOs();
    public void deleteProduct(Long productId);
    public Product updateProduct(Product product, Long productId);
    public Product updateProductByDTO(UpdateProductDTO updateProductDTO, Long productId);

    public Product addCategory(Long productId, Long categoryId);
    public Product removeCategory(Long productId, Long categoryId);

    public List<Product> findByProductNameContaining(String searchString);
    public List<Product> findByCategoryName(String categoryName);
    public List<Product> findByCategoryId(Long categoryId);
    public List<Product> findByCategoryIdList(List<Long> categoryIdList);

    public Product updateProductPrice(Long productId, Double price);
    public Double getDiscountedPrice(Long productId, Double discount);

    public Product stockToShelf(Long productId, Integer quantity);
    public Product shelfToStock(Long productId, Integer quantity);
}
