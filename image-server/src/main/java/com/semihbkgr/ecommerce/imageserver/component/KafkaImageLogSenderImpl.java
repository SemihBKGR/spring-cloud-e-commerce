package com.semihbkgr.ecommerce.imageserver.component;

import com.semihbkgr.ecommerce.modelcommon.image.Image;
import com.semihbkgr.ecommerce.modelcommon.message.LogMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

@Component
@RequiredArgsConstructor
public class KafkaImageLogSenderImpl implements KafkaImageLogSender {

    private final ReactiveKafkaProducerTemplate<Object, Object> kafkaProducerTemplate;

    @Value("${kafka.topics.log}")
    private String logTopic;

    @Override
    public Mono<SenderResult<Void>> log(@NonNull ActionType type, @NonNull Image image) {
        return kafkaProducerTemplate.send(logTopic, logMessageOf(type, image, null));
    }

    @Override
    public Mono<SenderResult<Void>> log(ActionType type, @NonNull Image image, @Nullable String actionBy) {
        return kafkaProducerTemplate.send(logTopic, logMessageOf(type, image, actionBy));
    }

    private LogMessage logMessageOf(@NonNull KafkaImageLogSender.ActionType type,
                                    @NonNull Image image,
                                    @Nullable String actionBy) {
        return LogMessage.builder()
                .actionType(type.name())
                .actionBy(actionBy)
                .actionData(image)
                .timeMs(System.currentTimeMillis())
                .build();
    }

}
