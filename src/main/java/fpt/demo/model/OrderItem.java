package fpt.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne//(cascade=CascadeType.PERSIST)
    @JsonIgnoreProperties(value = "orderItems")
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE) //when product is deleted, delete this orderitem
    private Product product;

    @ManyToOne//(cascade=CascadeType.PERSIST)
    @JsonIgnoreProperties(value = "items")
    //@JsonIgnore
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    private Order order;

    @Column(name="quantity")
    private Integer quantity;

}
