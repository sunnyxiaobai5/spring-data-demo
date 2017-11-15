package com.sunnyxiaobai5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        // connectionFactory.setHostName("10.6.24.51");
        // connectionFactory.setPort(6379);
        return connectionFactory;
    }
}
