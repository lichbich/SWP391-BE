package fpt.demo.model.dto;

import fpt.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullCategoryDTO {
    private Long id;
    private String categoryName;
    private String description;
    private Set<Product> products;
}
