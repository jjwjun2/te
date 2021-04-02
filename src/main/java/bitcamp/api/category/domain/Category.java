package bitcamp.api.category.domain;

import bitcamp.api.prd.domain.Product;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctg_no")
    private long ctgNo;

    @Column(name = "ctg_name")
    private String ctgName;

    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();
}