package com.redis.challenge.service;

import com.redis.challenge.model.Product;
import com.redis.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @CacheEvict(value = "products", allEntries = true)
    public Product save(Product product) {
        return repository.save(product);
    }

    @Cacheable(value = "products")
    public List<Product> getAll(){

        return repository.findAll();
    }

    @Cacheable(value = "productId")
    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    @CacheEvict(value = "productId")
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
