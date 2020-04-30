package com.nekochips.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author NekoChips
 * @description 消息监听器
 * @date 2020/4/30
 */
@Component
@Slf4j
public class KafkaMessageListener {

    /// 监听单个 topic，指定一个 consumer group
//    @KafkaListener(topics = "kafka_test", groupId = "kafka-consumer")
//    public void listen(String message) {
//        log.info("收到消息： {}", message)  
//    }

    /// 监听多个 topic，指定一个 consumer group，使用 @Header 获取消息来源信息
//    @KafkaListener(topics = {"kafka_test", "kafka_test2"}, groupId = "kafka-consumer")
//    public void listen(@Payload String message,
//                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        log.info("收到消息来自 topic: {}, partition: {} 的消息： {}", topic, partition, message);
//    }


    @KafkaListener(topics = {"kafka_test", "kafka_test2"}, groupId = "kafka-consumer")
    public void listen(@Payload String message,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("收到消息来自 topic: {}, partition: {} 的消息： {}", topic, partition, message);
    }
}
