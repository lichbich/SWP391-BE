package fpt.demo.model.dto;

import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewProductDTO {
    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private Integer soldQuantity;
    private Integer itemInShelf;
    private String imagePath;
    private Category category; // Assuming you want to associate categories using their IDs
    private Provider provider; // Assuming you want to associate a provider using its ID
}
