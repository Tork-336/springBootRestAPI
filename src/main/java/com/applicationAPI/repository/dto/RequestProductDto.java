package com.applicationAPI.repository.dto;

import com.applicationAPI.repository.entities.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class RequestProductDto {

    @NotEmpty
    @Size(min = 1)
    private List<Product> products;

    public RequestProductDto() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
