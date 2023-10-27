package fpt.demo.model.requestModel;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryListRequest {
    private Long productId;
    private List<Long> categoryIdList;
}
