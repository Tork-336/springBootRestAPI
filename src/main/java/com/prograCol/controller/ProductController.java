package com.prograCol.controller;

import com.prograCol.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    @ResponseBody()
    public Map<String, Object> getAllproducts() {
        try {
            return UtilResponse.mapOk(productService.getAll());
        } catch (Exception e) {
            return UtilResponse.mapError("Ocurrio un error!: \n" + e.getMessage());
        }
    }
}
