package fpt.demo.service.servceImp;


import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.repository.CategoryRepository;
import fpt.demo.repository.ProductRepository;
import fpt.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CategoryRepository categoryRepository;


    //add product: input field: productName, price, description, importPrice, itemInStock, imagePath
    @Override
    @Transactional
    public Product addProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setStatus(true);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();
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

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }


    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    public Product updateProduct(Product product, Long productId) {
        //Optional<Product> old_product = productRepository.findById(productId);
        product.setProductId(productId);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void addCategory(Long productId, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        Product product = productRepository.findById(productId).get();
        product.getCategoryList().add(category);
        productRepository.save(product);
        category.getProductList().add(product);
        categoryRepository.save(category);
        //return product;
    }

    @Override
    public void addCategoryList(Long productId, List<Long> categoryIdList) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            List<Category> categories = new ArrayList<Category>();
            for (Long categoryId : categoryIdList) {

                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new EntityNotFoundException("Category with ID " + categoryId + " not found."));
                categories.add(category);

//                Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
//                if (optionalCategory.isPresent()) {
//                    Category category = optionalCategory.get();
//                    categories.add(category);
//                } else {
//                    throw new EntityNotFoundException("Category with ID " + categoryId + " not found.");
//                }

            }
            product.getCategoryList().addAll(categories);
            productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product with ID " + productId + " not found.");
        }

    }

//    @Transactional
//    public void updateProduct(Integer productId) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "Product with id " + productId + " does not exist"));
//    }

}

