package bitcamp.api.category.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


import bitcamp.api.prd.domain.Product;
import lombok.Getter;

@Entity @Getter
@Table(name="categories")
public class Category {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ctg_no") private long ctgNo;
	@Column(name="ctg_name") private String ctgName;

	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();
}