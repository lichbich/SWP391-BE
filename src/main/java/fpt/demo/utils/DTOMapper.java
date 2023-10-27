package fpt.demo.utils;

import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.model.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOMapper {
    public CategoryDTO CategoryToDTO(Category category){
        return new CategoryDTO(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getProductList()
        );
    }
}
