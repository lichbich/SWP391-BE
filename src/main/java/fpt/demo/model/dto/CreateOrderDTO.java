package fpt.demo.model.dto;

import fpt.demo.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    private String receiver;
    private String address;
    private String phoneNum;
    private Set<OrderItem> orderItems;
}
