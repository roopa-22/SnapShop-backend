package com.snapshop.ecom.service;

import com.snapshop.ecom.exception.ProductException;
import com.snapshop.ecom.model.Product;
import com.snapshop.ecom.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public Product createProduct(CreateProductRequest req);

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId, Product req) throws ProductException;

    public Product findProductById(Long productId) throws ProductException;

    public List<Product> findProductsByCategory(String category);

    public Page<Product> getAllProducts(String category, List<String>colors,List<String>sizes, Integer minPrice, Integer maxPrice,
                                        Integer minDiscount, String sort,String stock, Integer pageNumber, Integer pageSize);

    public List<Product> findAllProducts();
}

