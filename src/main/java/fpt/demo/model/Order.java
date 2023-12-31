package fpt.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderId;

//    @OneToOne
//    @JoinColumn(name="customer_id",referencedColumnName = "user_id",insertable = false,updatable = false)
//    private User customer;

    @Column(name="receiver")
    private String receiver;

    @Column(name="address")
    private String address;

    @Column(name="phoneNum")
    private String phoneNum;

    @Column(name="order_created_at")
    private LocalDateTime createdAt;

    @Column(name="status")
    private boolean status;

    @OneToMany(mappedBy="order") //, cascade = CascadeType.PERSIST
    @JsonIgnoreProperties(value = "order")
    private List<OrderItem> items;

}
