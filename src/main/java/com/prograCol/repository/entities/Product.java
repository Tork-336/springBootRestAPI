package com.prograCol.repository.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "description")
    private String description;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Long price;
    @ManyToMany()
    @JoinTable(
            name = "product_category",
            joinColumns = {@JoinColumn(name = "id_product", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_category", referencedColumnName = "id")})
    private Set<Category> categories = new HashSet<>();

    public Product() {
    }

    public Product(LocalDateTime creationDate, String description, LocalDateTime expiryDate, String name, Long price) {
        this.creationDate = creationDate;
        this.description = description;
        this.expiryDate = expiryDate;
        this.name = name;
        this.price = price;
    }

    @PrePersist
    void preInsert() {
        if (Objects.isNull(this.creationDate)) {
            this.creationDate = LocalDateTime.now();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Set getCategories() {
        return categories;
    }

    public void setCategories(Set categories) {
        this.categories = categories;
    }
}
