package fpt.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profile_id;

    @Column(name="image_id",nullable = false)
    private int image_id;

    @Column(name="full_name",nullable = true)
    private String full_name;

    @Column(name="phone",nullable = true)
    private String phone;
    @Column(name="dob",nullable = true)
    private LocalDateTime dob;

    @Column(name="gender",nullable = false)
    private int gender;
    @Column(name="address",nullable = true)
    private String address;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="created_at",nullable = false)
    private LocalDateTime created_at;
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updated_at;
}
