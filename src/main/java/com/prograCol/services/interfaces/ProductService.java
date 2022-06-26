package com.prograCol.services.interfaces;

import com.prograCol.repository.dto.FilterDto;
import com.prograCol.repository.dto.ObjectListDto;
import com.prograCol.repository.entities.Product;

import java.sql.SQLTransientException;
import java.util.List;

public interface ProductService {

    List<Product> getAll() throws SQLTransientException;
    List<Product> getFilter(FilterDto filter);
    ObjectListDto getPagin(int page, int size);
    List<Product> create(List<Product> products);
    List<Product> update(List<Product> products);
    boolean delete(List<Product> products);
}
