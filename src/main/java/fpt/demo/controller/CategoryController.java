package fpt.demo.controller;


import fpt.demo.model.Category;
import fpt.demo.model.dto.FullCategoryDTO;
import fpt.demo.model.dto.ViewCategoryDTO;
import fpt.demo.model.requestModel.CategoryListRequest;
import fpt.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "api/v0_01/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    // Manager functionality
    // Add category
    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category added successfully!");
    }

    // Manager functionality
    // Add category list
    @PostMapping("/list/add")
    public ResponseEntity<String> addCategoryList(@RequestBody CategoryListRequest categoryListRequest){
        categoryService.addCategoryList(categoryListRequest);
        return ResponseEntity.ok("Category list added successfully!");
    }


    // Manager/Customer functionality
    // Get category by id
    @RequestMapping(path = "{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    // Manager functionality
    // Get category by id
    @RequestMapping(path = "/dto/{categoryId}")
    public ResponseEntity<ViewCategoryDTO> getViewCategoryDTOById(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.getViewCategoryDTOById(categoryId));
    }

    // Manager/Customer functionality
    // Get all categories list
    @RequestMapping("")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Manager/Customer functionality
    // Get all categoriy dtos list
    @RequestMapping("/dto")
    public ResponseEntity<List<ViewCategoryDTO>> getAllViewCategoryDTOs(){
        return ResponseEntity.ok(categoryService.getAllViewCategoryDTOs());
    }

    // Manager functionality
    // Delete category with id
    @DeleteMapping(path = "/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted!");
    }

    // Manager functionality
    // Update category with id or create a new category with id
    @PutMapping(path = "/update/{categoryId}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(category, categoryId));
    }

    // Manager functionality
    // Get full info of category (with products list)
    @GetMapping(path = "/full/{categoryId}")
    public ResponseEntity<FullCategoryDTO> getCategoryWithProductsById(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryWithProductsById(categoryId));
    }

//    @PutMapping(path = "/put/category")
//    public Category addProduct(@RequestBody Long categoryId, @RequestBody Long productId){
//        return categoryService.addCategory(categoryId, productId);
//    }
}
