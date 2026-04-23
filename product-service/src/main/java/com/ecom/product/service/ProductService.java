package com.ecom.product.service;

import com.ecom.product.dto.ProductRequest;
import com.ecom.product.dto.ProductResponse;
import com.ecom.product.model.Product;
import com.ecom.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest){
        System.out.println("before product creation");
        Product product = new Product(productRequest.id(), productRequest.name(),
                productRequest.description(), productRequest.price());

        productRepository.save(product);
      //  log.info("Product created Successfully");
        System.out.println("Product created Successfully");

        return new ProductResponse(product.getId(),
                product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll().stream().map(product->new ProductResponse(product.getId(),
                product.getName(), product.getDescription(), product.getPrice())).toList();
    }
}
