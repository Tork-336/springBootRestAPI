package com.prograCol.repository.entitys;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "categorySet", fetch = FetchType.LAZY)
    private Set<Product> productSet;

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getProductSet() {
        return productSet;
    }

    public void setProductSet(Set productSet) {
        this.productSet = productSet;
    }
}
