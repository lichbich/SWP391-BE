package fpt.demo.service;

import fpt.demo.model.Category;
import fpt.demo.model.dto.FullCategoryDTO;
import fpt.demo.model.dto.ViewCategoryDTO;
import fpt.demo.model.requestModel.CategoryListRequest;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);

    public List<Category> addCategoryList(CategoryListRequest categoryListRequest);

    public Category getCategoryById(Long categoryId);

    public ViewCategoryDTO getViewCategoryDTOById(Long categoryId);

    public List<Category> getAllCategories();

    public List<ViewCategoryDTO> getAllViewCategoryDTOs();

    public void deleteCategory(Long categoryId);

    public Category updateCategory(Category category, Long categoryId);

    public FullCategoryDTO getCategoryWithProductsById(Long categoryId);

}
