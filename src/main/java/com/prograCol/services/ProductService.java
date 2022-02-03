package com.prograCol.services;

import com.prograCol.repository.entitys.ProductRepository;
import com.prograCol.repository.entitys.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }
}
