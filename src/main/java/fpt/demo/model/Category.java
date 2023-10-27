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
    private long categoryId;


    @Column(name="category_name",nullable = false,length = 100)
    private String categoryName;

    @JsonIgnore
    //@JsonFilter("categoryFilter")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categoryList")
    @OnDelete(action = OnDeleteAction.SET_NULL) //CASCADE still work tho
    private List<Product> productList;

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
