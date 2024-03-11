package com.redis.challenge.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class RedisPublisherServiceImpl implements RedisPublisherService {

    public void publishMessage(String message){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.publish("canal1",message);

    }
}
