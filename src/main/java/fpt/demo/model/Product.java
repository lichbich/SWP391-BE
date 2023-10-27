package fpt.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private long productId;


    //@Column(name = "product_name", columnDefinition = "nvarchar(255)")

    @Column(name="product_name",nullable = false,length = 100)
    private String productName;

    @Column(name="product_price",nullable = false)
    private double price;


//    @Column(name = "description", columnDefinition = "nvarchar(255)")
//    private String description;
//
//    private double importPrice;
//
//    private int itemInStock;
//
//    private int itemInShelf;
//
//    @Column(name = "created_at", columnDefinition = "datetime")
//    private LocalDateTime createdAt;
//
//    @Column(name = "image_path", columnDefinition = "nvarchar(255)")
//    private String imagePath;
//
//    @Column(name = "status", columnDefinition = "boolean")

    @Column(name="product_description",nullable = true,length = 1000)
    private String description;

    @Column(name="product_import_price",nullable = false)
    private double importPrice;

    @Column(name="product_íntock")
    private int itemInStock;

    @Column(name="product_inuse")
    private int itemInShelf;

    @Column(name="product_create_at")
    private LocalDateTime createdAt;

    @Column(name="product_img_path")
    private String imagePath;

    @Column(name="status")
    private boolean status;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) //cascade = CascadeType.MERGE, orphanRemoval = true
//    @OnDelete(action = OnDeleteAction.SET_NULL)
    //@JsonIgnoreProperties({"categoryList"})
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "product_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "category_id",
                    nullable = true
            )
    )
    private List<Category> categoryList;

    @JsonIgnoreProperties(value = "productList") //when get the product, the provider class will not list the list of products
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) //, cascade = CascadeType.MERGE
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    private Provider provider;

//    @ManyToMany
//    @JoinTable(
//            name = "product_provider",
//            joinColumns = @JoinColumn(
//                    name = "product_id",
//                    referencedColumnName = "productId"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "provider_id",
//                    referencedColumnName = "providerId"
//            )
//    )
//    private List<Provider> providerList;

//    @ManyToMany
//    @JoinTable(

//            name="product_order",
//            joinColumns=@JoinColumn(
//                    name="product_id",
//                    referencedColumnName = "productId"
//            ),
//            inverseJoinColumns=@JoinColumn(
//                    name="order_id",
//                    referencedColumnName = "orderId"
//            )
//    )
//    private List<Order> orders;


//    @ManyToOne
//    @JoinColumn(name="category_id",referencedColumnName ="category_id",insertable = false,updatable = false)
//    private Category category;


//    @ManyToMany
//    @JoinTable(
//            name="product_cart",
//            joinColumns=@JoinColumn(
//                    name="product_id",
//                    referencedColumnName = "productId"
//            ),
//            inverseJoinColumns=@JoinColumn(
//                    name="cart_id",
//                    referencedColumnName = "cartId"
//            )
//    )
//    private List<Cart> carts;

    @OneToMany(mappedBy="product")
    @JsonIgnoreProperties(value = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy="product")
    @JsonIgnoreProperties(value = "product")
    private List<OrderItem> orderItems;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", importPrice=" + importPrice +
                ", itemInStock=" + itemInStock +
                ", itemInShelf=" + itemInShelf +
                ", createdAt=" + createdAt +
                ", imagePath='" + imagePath + '\'' +
                ", status=" + status +
                ", categoryList=" + categoryList +
                '}';
    }
}
