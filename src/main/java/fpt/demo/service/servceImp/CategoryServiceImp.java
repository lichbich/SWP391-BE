package fpt.demo.service.servceImp;

import fpt.demo.model.Category;
import fpt.demo.model.dto.CategoryDTO;
import fpt.demo.repository.CategoryRepository;
import fpt.demo.repository.ProductRepository;
import fpt.demo.service.CategoryService;
import fpt.demo.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DTOMapper mapper;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) {

//        return categoryRepository.findById(categoryId).get();

//        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
//        if(categoryOptional.isPresent()){
//            return categoryOptional.get();
//        }
//        return null;

        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>)categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long categoryId) {
          categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        category.setCategoryId(categoryId);
        return categoryRepository.save(category);
    }

    @Override
    public CategoryDTO getCategoryWithProductsById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return mapper.CategoryToDTO(category);
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
