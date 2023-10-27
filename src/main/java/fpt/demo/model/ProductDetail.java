package fpt.demo.model;

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
@Table(name="provider_product")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long provider_product_id;

    @Column(name="provider_id",nullable = false)
    private long provider_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="provider_id",referencedColumnName = "provider_id",insertable = false,updatable = false)
    private Provider provider;


    @Column(name="product_id",nullable = false)
    private long product_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id",referencedColumnName = "product_id",insertable = false,updatable = false)
    private Product product;

    @Column(name="quantity",nullable = false)
    private int quantity;

    @Column(name="price",nullable = false)
    private float price;

}
