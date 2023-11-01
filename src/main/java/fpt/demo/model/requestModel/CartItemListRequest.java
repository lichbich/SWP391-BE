package fpt.demo.model.requestModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemListRequest {
    private Long cartId;
    private List<Long> cartItemIdList;
}
