package com.semihbkgr.ecommerce.productionserver.component;

import com.semihbkgr.ecommerce.modelcommon.message.LogMessage;
import com.semihbkgr.ecommerce.modelcommon.production.Production;
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
public class KafkaProductionLogSenderImpl implements KafkaProductionLogSender {

    private final ReactiveKafkaProducerTemplate<Object, Object> kafkaProducerTemplate;

    @Value("${kafka.topics.log}")
    private String logTopic;

    @Override
    public Mono<SenderResult<Void>> log(ProductionActionType type, Production production) {
        return kafkaProducerTemplate.send(logTopic, logMessageOf(type, production, null));
    }

    @Override
    public Mono<SenderResult<Void>> log(ProductionActionType type, Production production, String actionBy) {
        return kafkaProducerTemplate.send(logTopic, logMessageOf(type, production, actionBy));
    }

    private LogMessage logMessageOf(@NonNull ProductionActionType type,
                                    @NonNull Production production,
                                    @Nullable String actionBy) {
        return LogMessage.builder()
                .actionType(type.name())
                .actionBy(actionBy)
                .actionData(production)
                .timeMs(System.currentTimeMillis())
                .build();
    }

}
