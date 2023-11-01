package fpt.demo.service.servceImp;

import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.model.dto.FullCategoryDTO;
import fpt.demo.model.dto.ViewCategoryDTO;
import fpt.demo.model.dto.ViewProductDTO;
import fpt.demo.model.requestModel.CategoryListRequest;
import fpt.demo.repository.CategoryRepository;
import fpt.demo.repository.ProductRepository;
import fpt.demo.service.CategoryService;
import fpt.demo.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DTOMapper dtoMapper;

    // Manager functionality
    // Add a new category
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Manager functionality
    // Add a new category list
    @Override
    public List<Category> addCategoryList(CategoryListRequest categoryListRequest){
        return categoryRepository.saveAll(categoryListRequest.getCategoryList());
    }

    // Manager/Customer functionality
    // Get category by id
    @Override
    public Category getCategoryById(Long categoryId) {

//        return categoryRepository.findById(categoryId).get();
//        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
//        if(categoryOptional.isPresent()){
//            return categoryOptional.get();
//        }
//        return null;

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
    }

    // Manager/Customer functionality
    // Get category dto by id
    @Override
    public ViewCategoryDTO getViewCategoryDTOById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
        ViewCategoryDTO viewCategoryDTO = dtoMapper.CategoryToViewCategoryDTO(category);
        return viewCategoryDTO;
    }

    // Manager functionality
    // Get all categories
    @Override
    public List<Category> getAllCategories() {
        return (List<Category>)categoryRepository.findAll();
    }

    // Manager functionality
    // Get all category dtos
    @Override
    public List<ViewCategoryDTO> getAllViewCategoryDTOs() {
        List<Category> categories = categoryRepository.findAll();
        List<ViewCategoryDTO> viewCategoryDTOS = new ArrayList<>();
        for(Category item : categories){
            viewCategoryDTOS.add(dtoMapper.CategoryToViewCategoryDTO(item));
        }
        return viewCategoryDTOS;
    }

    // Manager functionality
    // Delete category with id
    @Override
    public void deleteCategory(Long categoryId) {
          categoryRepository.deleteById(categoryId);
    }

    // Manager functionality
    // Update a category or add a new category
    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
        if(category.getCategoryName()!=null){
            existingCategory.setCategoryName(category.getCategoryName());
        }
        if(category.getDescription()!=null){
            existingCategory.setDescription(category.getDescription());
        }
        if(category.getProductSet()!=null){
            existingCategory.setProductSet(category.getProductSet());
        }
        return categoryRepository.save(existingCategory);
    }

    // Manager functionality
    // Get full info of category (with products list)
    @Override
    public FullCategoryDTO getCategoryWithProductsById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
        return dtoMapper.CategoryToDTO(category);
    }

//    //unfinished
//    @Transactional
//    public Category addProduct(Long categoryId, Long productId) {
//        Category cate = categoryRepository.findById(categoryId).get();
//        Product prod = productRepository.findById(productId).get();
//        prod.getCategoryList().add(cate);
//        productRepository.save(prod);
//        cate.getProductList().add(prod);
//        categoryRepository.save(cate);
//        return cate;
//    }
}
