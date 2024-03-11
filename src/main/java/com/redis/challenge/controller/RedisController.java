package com.redis.challenge.controller;

import com.redis.challenge.service.RedisPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    RedisPublisherService redisService;
    @PostMapping
    public ResponseEntity<?> publishMessage(@RequestBody String message) {
        redisService.publishMessage(message);
        return ResponseEntity.ok("mensagem publicada");
    }

}
