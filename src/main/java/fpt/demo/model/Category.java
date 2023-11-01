package fpt.demo.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {

    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name="category_name",nullable = false, length = 100)
    private String categoryName;

    @Column(name="category_description", length = 1000)
    private String description;

    @OnDelete(action = OnDeleteAction.SET_NULL)
    @OneToMany(fetch = FetchType.LAZY, mappedBy= "category")
    private Set<Product> productSet;

//    //@JsonFilter("categoryFilter")
//    @JsonIgnore
////    @OnDelete(action = OnDeleteAction.SET_NULL) //CASCADE still work tho
//    @ManyToMany(mappedBy="categorySet") //fetch = FetchType.LAZY
//    private Set<Product> productSet;

}
