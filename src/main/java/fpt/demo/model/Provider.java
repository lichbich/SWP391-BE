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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="provider_id")
    private Long providerId;

    @Column(name="provider_name", nullable = false, length = 200)
    private String providerName;

    @Column(name="provider_address", nullable = false, length = 200)
    private String providerAddress;

    @Column(name="provider_email",length = 200)
    private String providerEmail;

    @Column(name="provider_phone",length = 20)
    private String providerPhone;

    @Column(name="status")
    private Boolean status;

    @Column(name="provider_created_at", columnDefinition = "datetime", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy= "provider") // , cascade = CascadeType.PERSIST
//    @JsonIgnore // productList will not be shown in any way
    @JsonIgnoreProperties(value = "provider", allowSetters = true) //when a provider is called, it lists a products list, but the products list will not call the provider again
    @OnDelete(action = OnDeleteAction.SET_NULL) //no impact for now
    private Set<Product> productSet;

//    @ManyToMany(mappedBy = "providerList")
//    private List<Product> productList;

//    @OneToMany(fetch = FetchType.EAGER,mappedBy = "provider")
//    private Set<ProductDetail> productDetails;

}
