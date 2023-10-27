package fpt.demo.model.requestModel;

import lombok.Data;

@Data
public class CartItemDeleteRequest {
    private Long cartId;
    private Long cartItemId;
}
