package com.prograCol.repository;

import com.prograCol.repository.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product WHERE name =:name", nativeQuery = true)
    List<Product> findAllByName(@Param(value = "name") String name);
    @Query(value = "SELECT * FROM product WHERE price < :price", nativeQuery = true)
    List<Product> findAllByPriceMenor(@Param(value = "price") String price);
    @Query(value = "SELECT * FROM product p WHERE p.name LIKE :nameFirts%", nativeQuery = true)
    List<Product> findAllByNameFirst(@Param(value = "nameFirts") String nameFirts);
}
