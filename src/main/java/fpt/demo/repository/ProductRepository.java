package fpt.demo.repository;

import fpt.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{

    public List<Product> findByProductNameContaining(String searchString);

    @Query("SELECT p FROM Product p WHERE p.category.categoryName LIKE %:keyword%")
    public List<Product> findByCategoryNameContainingKeyword(String keyword);

    public List<Product> findByCategoryCategoryId(Long categoryId);

    public List<Product> findByCategoryCategoryIdIn(List<Long> categoryIdList);
}