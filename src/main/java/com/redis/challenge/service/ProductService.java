package com.redis.challenge.service;

import com.redis.challenge.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    List<Product> getAll();
    Optional<Product> getById(Long id);
    void deleteById(Long id);
}
