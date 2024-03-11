package com.redis.challenge.service;

import com.redis.challenge.model.Product;
import com.redis.challenge.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    RedisRepository repository;

    public void save(Product product){
        repository.save(product);
    }
}
