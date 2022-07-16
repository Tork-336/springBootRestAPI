package com.prograCol.controller;

import com.prograCol.repository.dto.FilterDto;
import com.prograCol.repository.dto.ObjectListDto;
import com.prograCol.repository.dto.RequestProductDto;
import com.prograCol.repository.entities.Product;

import com.prograCol.services.interfaces.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.prograCol.util.ConstantesMensaje.messageListData;

@CrossOrigin(value = "*")
@RestController()
public class ProductController {

    private Log LOG = LogFactory.getLog(ProductController.class);
    @Autowired
    private ProductService service;

    @GetMapping(value = "/product")
    @ResponseBody()
    public ResponseEntity getAllproducts(@RequestParam(name = "all", required = false) boolean all
                                        , @RequestParam(name = "page") Integer page
                                        , @RequestParam(name = "size") Integer size) {
        ObjectListDto response = service.getPagin(page, size);
        return new ResponseEntity(new ResponseGeneralAPI<>(response.getData(), "Exito obteniendo los registros.", (int) response.getTotal(), true), HttpStatus.OK);
    }

    @PostMapping(value = "/product/filter")
    @ResponseBody
    public ResponseEntity<ResponseGeneralAPI> filterProduct(@RequestBody @Valid FilterDto filter) {
        return new ResponseEntity<>(new ResponseGeneralAPI(service.getFilter(filter), messageListData, true), HttpStatus.OK);
    }

    @PostMapping(value = "/product")
    @ResponseBody
    public ResponseEntity<ResponseGeneralAPI> createProduct(@RequestBody RequestProductDto body) {
        List<Product> insertProduct = service.create(body.getProducts());
        if (insertProduct.isEmpty()) {
            return new ResponseEntity<>(new ResponseGeneralAPI(null, "Se crearon los regitros.", true), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ResponseGeneralAPI(insertProduct, "No se crearon los siguinetes registros.", false), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping(value = "/product")
    @ResponseBody
    public ResponseEntity<ResponseGeneralAPI> updateProduct(@RequestBody RequestProductDto body) {
        List<Product> updateProduct = service.update(body.getProducts());
        if (updateProduct.isEmpty()) {
            return new ResponseEntity<>(new ResponseGeneralAPI(null, "Se actualizaron los regitros.", false), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ResponseGeneralAPI(updateProduct, "No se actualizaron los siguinetes registros.", true), HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/product")
    @ResponseBody
    public ResponseEntity<ResponseGeneralAPI> deteleteProduct(@RequestBody RequestProductDto body) {
        boolean isDelete = service.delete(body.getProducts());
        if (isDelete) {
            return new ResponseEntity<>(new ResponseGeneralAPI(null , "Se eliminaron los regitros.", true), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new ResponseGeneralAPI(null , "No se eliminaron los regitros.", false), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
