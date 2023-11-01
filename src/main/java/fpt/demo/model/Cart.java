package fpt.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long cartId;

    //cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    @OneToMany(mappedBy="cart") //persist, merge will not add the cartItems
    @JsonIgnoreProperties(value = "cart")
    private Set<CartItem> items;


}
