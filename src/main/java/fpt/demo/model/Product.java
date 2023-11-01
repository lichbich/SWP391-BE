package fpt.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;

    //@Column(name = "product_name", columnDefinition = "nvarchar(255)")
    @Column(name="product_name", nullable = false, length = 100)
    private String productName;

    @Column(name="product_price", nullable = false)
    private Double price;

    @Column(name="product_import_price", nullable = false)
    private Double importPrice;

    //@Column(name = "description", columnDefinition = "nvarchar(255)")
    @Column(name="product_description", nullable = true, length = 1000)
    private String description;

    //@Column(name = "image_path", columnDefinition = "nvarchar(255)")
    @Column(name="product_img_path")
    private String imagePath;

    @Column(name="product_instock")
    private Integer itemInStock;

    @Column(name="product_inuse")
    private Integer itemInShelf;

    @Column(name = "sold_quantity")
    private Integer soldQuantity;

    //@Column(name = "created_at", columnDefinition = "datetime")
    @Column(name="product_created_at", columnDefinition = "datetime", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name="product_updated_at", columnDefinition = "datetime", updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    //@Column(name = "status", columnDefinition = "boolean")
    @Column(name="status")
    private Boolean status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="category_id",referencedColumnName ="category_id")
    private Category category;

    @JsonIgnoreProperties(value = "productSet") //when get the product, the provider class will not list the list of products
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}) //, cascade = CascadeType.MERGE     //   fetch = FetchType.EAGER,
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    @OnDelete(action = OnDeleteAction.SET_NULL) //when provider is deleted, set null to provider
    private Provider provider;

    @OneToMany(mappedBy="product")
    @JsonIgnoreProperties(value = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy="product")
    @JsonIgnoreProperties(value = "product")
    private Set<OrderItem> orderItems;

    //@JsonIgnoreProperties({"categorySet"})
////    @OnDelete(action = OnDeleteAction.SET_NULL)
//    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE}) //cascade = CascadeType.PERSIST fetch = FetchType.EAGER,
//    @JoinTable(
//            name = "product_category",
//            joinColumns = @JoinColumn(
//                    name = "product_id"
//                    ,referencedColumnName = "product_id"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "category_id"
//                    ,referencedColumnName = "category_id"
//                    //,nullable = true
//            )
//    )
//    private Set<Category> categorySet;

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



}
