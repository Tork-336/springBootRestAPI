package com.prograCol.repository.dto;

import com.prograCol.repository.entities.Product;

import java.util.List;

public class RequestProductDTO {

    private List<Product> products;

    public RequestProductDTO() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
