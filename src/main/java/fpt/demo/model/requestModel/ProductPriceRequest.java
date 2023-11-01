package fpt.demo.model.requestModel;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceRequest {
    private Long productId;
    private Double price;
    //this is the discount of the product
    //example: 75 means a 75% discount, 20 means a 20% discount, 10.5 means a 10.5% discount
    @Nullable
    private Double discount;
}
