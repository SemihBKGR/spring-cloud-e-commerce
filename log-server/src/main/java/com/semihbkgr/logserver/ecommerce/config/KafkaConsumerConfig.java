package com.semihbkgr.logserver.ecommerce.config;

import com.semihbkgr.ecommerce.modelcommon.message.LogMessage;
import com.semihbkgr.logserver.ecommerce.component.LogMessageLogger;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final LogMessageLogger logMessageLogger;
    @Value("${kafka.bootstrap-server}")
    private String bootstrapServer;
    @Value("${kafka.topics.log}")
    private String logTopic;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return props;
    }

    @Bean
    public ReceiverOptions<String, LogMessage> receiverOptions() {
        return ReceiverOptions.<String, LogMessage>create(consumerConfigs())
                .subscription(Collections.singleton(logTopic));
    }

    @Autowired
    public void subscribeKafkaLogReceiver(ReceiverOptions<String, LogMessage> receiverOptions) {
        KafkaReceiver.create(receiverOptions)
                .receiveAutoAck()
                .concatMap(rr -> rr)
                .subscribe(rr -> logMessageLogger.log(rr.value()));
    }

}
