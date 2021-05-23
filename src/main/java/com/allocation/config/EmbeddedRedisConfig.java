package com.allocation.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import java.util.Optional;

@Slf4j
@Configuration
@Profile("local")
@RequiredArgsConstructor
public class EmbeddedRedisConfig implements InitializingBean , DisposableBean {

    private final RedisProperties redisProperties;

    private RedisServer redisServer;

    @Override
    public void destroy() {
        Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
    }

    @Override
    public void afterPropertiesSet() {
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
    }
}
