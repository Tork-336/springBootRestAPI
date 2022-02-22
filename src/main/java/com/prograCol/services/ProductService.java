package com.prograCol.services;

import com.prograCol.repository.CategoryRepository;
import com.prograCol.repository.ProductRepository;
import com.prograCol.repository.dto.FilterDTO;
import com.prograCol.repository.entities.Category;
import com.prograCol.repository.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAll() {
        List<Product> retorno = new ArrayList<>(0);
        Iterator<Product> products = productRepository.findAll().iterator();
        while (products.hasNext()) {
            retorno.add(products.next());
        }
        return retorno;
    }

    public List<Product> getFilter(FilterDTO filter) {
        List<Product> retorno = new ArrayList<>(0);
        switch (filter.getName()) {
            case "name":
                retorno = productRepository.findAllByName(filter.getValue());
                break;
            case "price":
                retorno = productRepository.findAllByPriceMenor(filter.getValue());
                break;
            case "nameFirts":
                retorno = productRepository.findAllByNameFirst(filter.getValue());
                break;
        }
        return retorno;
    }

    public List<Product> getPagin(int page, int size) {
        List<Product> retorno = new ArrayList<>(0);
        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        if (page < products.getTotalPages()) {
            retorno = products.getContent();
        }
        return retorno;
    }

    public List<Product> create(List<Product> products) {
        int indexSaveProducts = 0;
        products.stream().forEach(product -> {
            product.setCreationDate(LocalDateTime.now());
            product.setExpiryDate(LocalDateTime.now());
            if (!product.getCategorySet().isEmpty()) {
                product.setCategorySet(findCategoryToPersistProduct(product));
            }
        });
        Iterator<Product> productsInsert = productRepository.saveAll(products).iterator();
        while (productsInsert.hasNext()) {
            if (Objects.nonNull(productsInsert.next())) {
                indexSaveProducts++;
            }
        }
        if (indexSaveProducts != products.size()) {
            List<Product> productsErrorInsert = new ArrayList<>(0);
            for (int i = 0; i < indexSaveProducts; i++) {
                productsErrorInsert.add(products.get(i));
            }
            return productsErrorInsert;
        }
        return new ArrayList<>(0);
    }

    private Set<Category> findCategoryToPersistProduct(Product product) {
        Set<Category> retorno = new HashSet<>(0);
        Iterator categorys = product.getCategorySet().stream().iterator();
        while (categorys.hasNext()) {
            Object obj = categorys.next();
            Category findCategory = categoryRepository.findById((Integer) ((Map) obj).get("id")).get();
            retorno.add(findCategory);
        }
        return retorno;
    }

    public List<Product> update(List<Product> products) {
        return create(products);
    }

    public boolean delete(List<Product> products) {
        boolean delete = false;
        try {
            productRepository.deleteAll(productRepository.findAllById(products.stream().map(product -> product.getId()).collect(Collectors.toList())));
            delete = true;
        } catch (Exception e) {
            throw e;
        }
        return delete;
    }
}
