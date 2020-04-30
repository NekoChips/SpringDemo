package com.nekochips.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nekochips.kafka.bean.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author NekoChips
 * @description Kafka 测试Controller
 * @date 2020/4/29
 */
@Slf4j
@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AdminClient adminClient;

    @PutMapping("topic")
    public void buildTopic(@RequestParam("name") String name, @RequestParam("numPartitions") Integer numPartitions,
                           @RequestParam("replicationFactor") Short replicationFactor) {
        NewTopic newTopic = new NewTopic(name, numPartitions, replicationFactor);
        adminClient.createTopics(Collections.singletonList(newTopic));
    }

    @GetMapping("topics")
    public HttpEntity<?> getAllTopics() {
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        try {
            Set<String> names = listTopicsResult.names().get();
            return ResponseEntity.ok(names);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务异常");
        }
    }

    @GetMapping("produce")
    public void send(@RequestParam("message") String message) {
        /// 发送消息至默认topic
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.sendDefault(message);
        buildCallBack(future, message);
    }

    @GetMapping("produce/{topic}")
    public void sendToTopic(@PathVariable String topic, @RequestParam("message") String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        buildCallBack(future, message);
    }
    
    @PostMapping("produce/order/{topic}")
    public void sendOrderToTopic(@PathVariable String topic, @RequestBody Order order) {
        String message = null;
        try {
            message = objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            log.error("parse json error", e);
        }
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        buildCallBack(future, message);
    }

    @DeleteMapping("topic/{topic}")
    public void deleteTopic(@PathVariable String topic) {
        adminClient.deleteTopics(Collections.singletonList(topic));
    }
    
    @SuppressWarnings("NullableProblems")
    private void buildCallBack(ListenableFuture<SendResult<String, String>> future, String message) {
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            
            @Override
            public void onFailure( Throwable throwable) {
                log.info("消息 [{}] 发送失败，错误原因: {}", message, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("消息 [{}] 发送成功，当前 partition: {}，当前 offset: {}", message,
                        result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            }
        });
    }
}
