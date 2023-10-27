package fpt.demo.model.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductCategoryRequest {
    private Long productId;
    private Long categoryId;
}
