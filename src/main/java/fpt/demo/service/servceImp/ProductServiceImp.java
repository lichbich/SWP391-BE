package fpt.demo.service.servceImp;


import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.model.dto.CreateProductDTO;
import fpt.demo.model.dto.UpdateProductDTO;
import fpt.demo.model.dto.ViewProductDTO;
import fpt.demo.repository.CategoryRepository;
import fpt.demo.repository.ProductRepository;
import fpt.demo.service.ProductService;
import fpt.demo.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DTOMapper dtoMapper;


    // Manager functionality
    // Add product: input field: productName, price, description, importPrice,
    // itemInStock, imagePath
    @Override
    @Transactional
    public Product addProduct(Product product) {
        product.setSoldQuantity(0);
        product.setItemInShelf(0);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setStatus(true); //default setting sets to true
        return productRepository.save(product);
    }

    // Manager functionality
    // Add product: input field: productName, price, description, importPrice,
    // itemInStock, imagePath
    @Override
    @Transactional
    public Product addProductByDTO(CreateProductDTO createProductDTO) {
        // Create a new Product entity and set its properties based on the DTO
        // Category and provider are associated by ids
        Product product = dtoMapper.CreateProductDTOToProduct(createProductDTO);
        product.setSoldQuantity(0);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setStatus(true); //default setting sets to true
        return productRepository.save(product);
    }

    // Manager functionality
    // Add a list of product dtos
    @Override
    @Transactional
    public List<Product> addProductByDTOs(List<CreateProductDTO> createProductDTOs){
        List<Product> products = new ArrayList<>();
        for(CreateProductDTO item : createProductDTOs){
            products.add(addProductByDTO(item));
        }
        //Deprecated
//        return productRepository.saveAll(dtoMapper.CreateProductDTOsToProducts(createProductDTOs));
        return productRepository.saveAll(products);
    }

    // Manager functionality
    // Get product with id
    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found."));
    }

    // Customer functionality
    // Customer can only view: productName, description, price(sell price), soldQuantity,
    // itemInShelf, imagePath, categoryIds(to list categories), providerName
    @Override
    public ViewProductDTO getViewProductDTOById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found."));
        ViewProductDTO viewProductDTO = dtoMapper.ProductToViewProductDTO(product);
        return viewProductDTO;
    }

//    @Override
//    public Product getProductById(Long productId) {
//        Optional<Product> product = productRepository.findById(productId);
//        if (product.isPresent()) {
//            return product.get();
//        } else {
//            throw new EntityNotFoundException("Product with id " + productId + " not found");
//        }
//    }

    // Manager functionality
    // Get all products list
    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    // Customer functionality
    @Override
    public List<ViewProductDTO> getAllViewProductDTOs() {

        List<Product> products = productRepository.findAll();
        return (List<ViewProductDTO>) dtoMapper.ProductsToViewProductDTOList(products);
    }

    // Manager functionality
    // Delete product with id
    @Override
    public void deleteProduct(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException ex) {
            // Handle the exception when the product with the given ID is not found
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }
    }

//    @Override
//    public void deleteProduct(Long productId) {
//        productRepository.deleteById(productId);
//    }

    // Manager functionality
    // Update product
    @Transactional
    @Override
    public Product updateProduct(Product product, Long productId) {
        // Check if the product with the specified ID exists
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found"));
        // Update the product and return the updated instance
        if(product.getProductName()!= null){
            existingProduct.setProductName(product.getProductName());
        }
        if(product.getPrice() != null){
            existingProduct.setPrice(product.getPrice());
        }
        if(product.getImportPrice()!=null){
            existingProduct.setProductName(product.getProductName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getImagePath() != null) {
            existingProduct.setImagePath(product.getImagePath());
        }
        if (product.getItemInStock() != null) {
            existingProduct.setItemInStock(product.getItemInStock());
        }
        if (product.getItemInShelf() != null) {
            existingProduct.setItemInShelf(product.getItemInShelf());
        }
        if (product.getSoldQuantity() != null) {
            existingProduct.setSoldQuantity(product.getSoldQuantity());
        }
        // createdAt field is by default existing
        if (product.getStatus() != null) {
            existingProduct.setStatus(product.getStatus());
        }
        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }
        if (product.getProvider() != null) {
            existingProduct.setProvider(product.getProvider());
        }
        if (product.getCartItems() != null) {
            existingProduct.setCartItems(product.getCartItems());
        }
        if (product.getOrderItems() != null) {
            existingProduct.setOrderItems(product.getOrderItems());
        }
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(existingProduct);
    }

    // Manager functionality
    // Update product
    @Transactional
    @Override
    public Product updateProductByDTO(UpdateProductDTO updateProductDTO, Long productId) {
        // Check if the product with the specified ID exists
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found"));
        Product product = dtoMapper.UpdateProductDTOToProduct(updateProductDTO); //deprecated
        // Update the product and return the updated instance

        if(product.getProductName() != null){
            existingProduct.setProductName(product.getProductName());
        }
        if(product.getPrice() != null){
            existingProduct.setPrice(product.getPrice());
        }
        if(product.getImportPrice() != null){
            existingProduct.setProductName(product.getProductName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getImagePath() != null) {
            existingProduct.setImagePath(product.getImagePath());
        }
        if (product.getItemInStock() != null) {
            existingProduct.setItemInStock(product.getItemInStock());
        }
        if (product.getItemInShelf() != null) {
            existingProduct.setItemInShelf(product.getItemInShelf());
        }
        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }
        if (product.getProvider() != null) {
            existingProduct.setProvider(product.getProvider());
        }


        existingProduct.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(existingProduct);
    }

    //---------------------------------------------------------------------------------
    // Manager functionality
    // Add category
    //failed
    @Override
    @Transactional
    public Product addCategory(Long productId, Long categoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found"));
        if(product.getCategory()!=null){
            return product;
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
        product.setCategory(category);
        product.setUpdatedAt(LocalDateTime.now());
        category.getProductSet().add(product);
        productRepository.save(product);
        categoryRepository.save(category);
        return product;
    }

    // Manager functionality
    //failed
    @Override
    public Product removeCategory(Long productId, Long categoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found"));
        if(product.getCategory()==null){
            return product;
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
        product.setCategory(null);
        product.setUpdatedAt(LocalDateTime.now());
        category.getProductSet().remove(product);
        productRepository.save(product);
        categoryRepository.save(category);
        return product;
    }

    // Manager functionality
    @Override
    public List<Product> findByProductNameContaining(String searchString) {
        return productRepository.findByProductNameContaining(searchString);
    }

    // Customer functionality
    @Override
    public List<Product> findByCategoryName(String categoryName) {
        //Category category = categoryRepository.findByCategoryNameContaining(categoryName);
        return productRepository.findByCategoryNameContainingKeyword(categoryName); //categoryRepository.findById(category.getCategoryId()).get()
    }

    // Customer functionality
    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }

    // Customer functionality
    @Override
    public List<Product> findByCategoryIdList(List<Long> categoryIdList) {
        return productRepository.findByCategoryCategoryIdIn(categoryIdList);
    }

    // Manager functionality
    @Override
    public Product updateProductPrice(Long productId, Double price) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found!"));
        product.setPrice(price);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    // Manager functionality
    @Override
    public Double getDiscountedPrice(Long productId, Double discount) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found!"));
        Double price = product.getPrice() - product.getPrice()*discount/100;
        return price;
    }

    // Manager functionality
    @Override
    @Transactional
    public Product stockToShelf(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found!"));
        Integer stock = product.getItemInStock();
        Integer shelf = product.getItemInShelf();
        if(stock < quantity){
            throw new IllegalArgumentException("There is not enough items in stock!");
        }
        stock -= quantity;
        shelf += quantity;
        product.setItemInStock(stock);
        product.setItemInShelf(shelf);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    // Manager functionality
    @Override
    @Transactional
    public Product shelfToStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found!"));
        Integer stock = product.getItemInStock();
        Integer shelf = product.getItemInShelf();
        if(shelf < quantity){
            throw new IllegalArgumentException("There is not enough items in shelf!");
        }
        shelf -= quantity;
        stock += quantity;
        product.setItemInStock(stock);
        product.setItemInShelf(shelf);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

//    @Transactional
//    public void updateProduct(Integer productId) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "Product with id " + productId + " does not exist"));
//    }

}

