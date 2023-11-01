package fpt.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProviderDTO {
    private String providerName;
    private String providerAddress;
    private String providerEmail;
    private String providerPhone;
}
