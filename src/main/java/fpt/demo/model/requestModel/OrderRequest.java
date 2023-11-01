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
public class OrderRequest {
    private Long cartId;
    private List<Long> cartItemIdList;
    private String receiver;
    private String address;
    private String phoneNum;
}
