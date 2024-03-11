package com.redis.challenge.repository;
import com.redis.challenge.model.Product;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public RedisRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(Product product){
        hashOperations.put("USER", "1", "lalazinho");
    }


}
