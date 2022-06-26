package com.prograCol.services.implementation;

import com.prograCol.controller.ProductController;
import com.prograCol.execption.BusinessLogicException;
import com.prograCol.execption.ModelNotFoundExecption;
import com.prograCol.repository.CategoryRepository;
import com.prograCol.repository.ProductRepository;
import com.prograCol.repository.dto.FilterDto;
import com.prograCol.repository.dto.ObjectListDto;
import com.prograCol.repository.entities.Category;
import com.prograCol.repository.entities.Product;
import com.prograCol.services.interfaces.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLTransientException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.prograCol.util.ConstantesMensaje.messageError;


@Service
public class ProductServiceImp<T> implements ProductService {

    private Log LOG = LogFactory.getLog(ProductController.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImp(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAll() throws SQLTransientException {
        List<Product> retorno = new ArrayList<>(0);
        Iterator<Product> products = productRepository.findAll().iterator();
        if (products.hasNext()) {
            while (products.hasNext()) {
                retorno.add(products.next());
            }
        } else {
            throw new ModelNotFoundExecption(messageError);
        }
        return retorno;
    }

    @Override
    public List<Product> getFilter(FilterDto filter) {
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
        if (retorno.isEmpty()) {
            throw new ModelNotFoundExecption(messageError);
        }
        return retorno;
    }

    @Override
    public ObjectListDto getPagin(int page, int size) {
        List<Product> retorno;
        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        if (page < products.getTotalPages()) {
            retorno = products.getContent();
        } else {
            throw new ModelNotFoundExecption(messageError);
        }
        return new ObjectListDto(retorno, products.getTotalElements());
    }

    @Override
    public List<Product> create(List<Product> products) {
        int indexSaveProducts = 0;
        products.stream().forEach(product -> {
            product.setCreationDate(LocalDateTime.now());
            product.setExpiryDate(LocalDateTime.now());
            if (!product.getCategories().isEmpty()) {
                product.setCategories(findCategoryToPersistProduct(product));
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
        Iterator categorys = product.getCategories().stream().iterator();
        while (categorys.hasNext()) {
            Object obj = categorys.next();
            Category findCategory = categoryRepository.findById((Integer) ((Map) obj).get("id")).get();
            retorno.add(findCategory);
        }
        return retorno;
    }

    @Override
    public List<Product> update(List<Product> products) {
        return create(products);
    }

    @Override
    public boolean delete(List<Product> products) {
        boolean delete = false;
        try {
            productRepository.deleteAll(productRepository.findAllById(products.stream().map(product -> product.getId()).collect(Collectors.toList())));
        } catch (Exception e) {
            throw new BusinessLogicException("No se logro eliminar la lista de entidades.");
        }
        return true;
    }
}
