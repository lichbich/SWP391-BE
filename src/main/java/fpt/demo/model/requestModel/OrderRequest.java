package fpt.demo.model.requestModel;

import lombok.Data;

@Data
public class OrderRequest {
    private Long cartId;
    private String receiver;
    private String address;
    private String phoneNum;
}
