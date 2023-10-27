package fpt.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE) //detach, merge, all
    @JsonIgnoreProperties(value = "items") //without this, when a product is called and the cartItems field is called
//    , the items field will list all the products in each of the item
    @JsonIgnore //this is for when getting a product, i dont want the cartItems field to list the cart field
    @JoinColumn(name="cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = "cartItems")
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name="quantity")
    private int quantity;
}
