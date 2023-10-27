package fpt.demo.controller;


import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.model.dto.CategoryDTO;
import fpt.demo.service.CategoryService;
import fpt.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping(path = "api/v0_01/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //add category
    @PostMapping("/add")
    public String addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return "Category added successfully!";
    }

    //get category by id
    @RequestMapping(path = "{categoryId}")
    public Category getCategoryById(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    //get all categories list
    @RequestMapping("")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @DeleteMapping(path = "/delete/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        //return "Category deleted!";
    }

    @PutMapping(path = "/update/{categoryId}")
    public Category updateCategory(@RequestBody Category category, @PathVariable("categoryId") Long categoryId){
        return categoryService.updateCategory(category, categoryId);
    }

    @GetMapping(path = "/full/{categoryId}")
    public CategoryDTO getCategoryWithProductsById(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategoryWithProductsById(categoryId);
    }

//    @PutMapping(path = "/put/category")
//    public Category addProduct(@RequestBody Long categoryId, @RequestBody Long productId){
//        return categoryService.addCategory(categoryId, productId);
//    }
}
