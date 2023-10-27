package fpt.demo.service;

import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.model.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Category addCategory(Category category);

    public Category getCategoryById(Long categoryId);

    public List<Category> getAllCategories();

    public void deleteCategory(Long categoryId);

    public Category updateCategory(Category category, Long categoryId);

    public CategoryDTO getCategoryWithProductsById(Long categoryId);

}
