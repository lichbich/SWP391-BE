package fpt.demo.model.requestModel;

import fpt.demo.model.Category;
import fpt.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListRequest {
    private List<Product> productList;
}
