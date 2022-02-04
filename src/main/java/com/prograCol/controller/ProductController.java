package com.prograCol.controller;

import com.prograCol.repository.entitys.Product;
import com.prograCol.services.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController()
public class ProductController {

    private Log LOG = LogFactory.getLog(ProductController.class);
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product")
    @ResponseBody()
    public Map<String, Object> getAllproducts (@RequestParam(name = "all", required = false) boolean all , @RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {
        try {
            if (all) {
                return UtilResponse.mapOk(productService.getAll());
            } else {
                return UtilResponse.mapOk(productService.getPagin(page, size));
            }
        } catch (Exception e) {
            LOG.error("  ProductController  ->  getAllproducts  Fallo: ", e);
            return UtilResponse.mapError("Ocurrio un error!: " + e.getMessage());
        }
    }



    @PostMapping(value = "/product")
    @ResponseBody
    public Map<String, Object> createProduct (@RequestBody List<Product> products) {
        try {
            List<Product> insertProduct = productService.create(products);
            if (insertProduct.size() > 0) {
                return UtilResponse.mapError(" Error insertando los registros", insertProduct);
            } else {
                return UtilResponse.mapOk(new ArrayList(0));
            }
        } catch (Exception e) {
            LOG.error("  ProductController  ->  createProduct  Fallo: ", e);
            return UtilResponse.mapError("Ocurrio un error!: " + e.getMessage());
        }
    }

    @PutMapping(value = "/product")
    @ResponseBody
    public Map<String, Object> updateProduct(@RequestBody List<Product> products) {
        try {
            List<Product> updateProduct = productService.update(products);
            if (updateProduct.size() > 0) {
                return UtilResponse.mapError(" Error actualizando los registros", updateProduct);
            } else {
                return UtilResponse.mapOk(new ArrayList(0));
            }
        } catch (Exception e) {
            LOG.error("  ProductController  ->  updateProduct  Fallo: ", e);
            return UtilResponse.mapError("Ocurrio un error!: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/product")
    @ResponseBody
    public Map<String, Object> deteleteProduct(@RequestBody List<Product> products) {
        try {
            if (productService.delete(products)) {
                return UtilResponse.mapOk(new ArrayList(0));
            }
            return UtilResponse.mapOk(new ArrayList(0));
        } catch (Exception e) {
            LOG.error("  ProductController  ->  updateProduct  Fallo: ", e);
            return UtilResponse.mapError("Ocurrio un error!: " + e.getMessage());
        }
    }
}
