package fpt.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {
    private String productName;
    private Double price;
    private Double importPrice;
    private String description;
    private Integer itemInStock;
    private Integer itemInShelf;
    private String imagePath;
    private Long categoryId; // Assuming you want to associate categories using their IDs
    private Long providerId; // Assuming you want to associate a provider using its ID
}
