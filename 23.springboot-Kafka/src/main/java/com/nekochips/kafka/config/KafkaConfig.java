package com.nekochips.kafka.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NekoChips
 * @description Kafka 配置类
 * @date 2020/4/30
 */
@Configuration
@EnableKafka
public class KafkaConfig{

    @Autowired
    private KafkaProperties kafkaProperties;
    
    @Bean
    public AdminClient adminClient() {
        Map<String, Object> configs = new HashMap<>(2);
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return AdminClient.create(configs);
    }
}
