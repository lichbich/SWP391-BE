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
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name="username",nullable = true)
    private String username;

    @Column(name="password",nullable = true)
    private String password;

    @Column(name="role_id",nullable = true)
    private long role_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id",referencedColumnName = "role_id",insertable = false,updatable = false)
    private Role role;

    @Column(name="profile_id",nullable = true)
    private long profile_id;

    @OneToOne
    @JoinColumn(name="profile_id",referencedColumnName ="profile_id",insertable = false,updatable = false)
    private Profile profile;

    @Column(name="is_active",nullable = true)
    private int is_active;

}
