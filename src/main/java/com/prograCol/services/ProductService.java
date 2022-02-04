package com.prograCol.services;

import com.prograCol.repository.CategoryRepository;
import com.prograCol.repository.ProductRepository;
import com.prograCol.repository.entitys.Category;
import com.prograCol.repository.entitys.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
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
        List<Product> updateProducts = new ArrayList<>(0);
        int indexSaveProducts = 0;
        Iterator<Product> currentProductUpdates = productRepository.findAllById(products.stream().map(product -> product.getId()).collect(Collectors.toList())).iterator();
        while (currentProductUpdates.hasNext()) {
            Product updateProduct = products.stream().filter(product -> product.getId().equals(currentProductUpdates.next().getId())).findAny().get();
            Product currentData = products.stream().filter(product -> product.getId().equals(updateProduct.getId())).findAny().get();
            updateProduct.setCreationDate(currentData.getCreationDate());
            updateProduct.setDescription(currentData.getDescription());
            updateProduct.setExpiryDate(currentData.getExpiryDate());
            updateProduct.setName(currentData.getName());
            updateProduct.setPrice(currentData.getPrice());
            updateProducts.add(updateProduct);
        }
        return create(updateProducts);
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
