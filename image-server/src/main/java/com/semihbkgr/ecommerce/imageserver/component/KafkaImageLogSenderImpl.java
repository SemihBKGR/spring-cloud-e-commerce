package com.semihbkgr.ecommerce.imageserver.component;

import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import com.semihbkgr.ecommerce.modelcommon.message.LogMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@RequiredArgsConstructor
public class KafkaImageLogSenderImpl implements KafkaImageLogSender {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Value("${kafka.topics.log}")
    private String logTopic;

    @Override
    public ListenableFuture<SendResult<Object, Object>> log(ActionType type, ProfileImage image, String actionBy) {
        return kafkaTemplate.send(logTopic, logMessageOf(type, image, actionBy));
    }

    @Override
    public ListenableFuture<SendResult<Object, Object>> log(ActionType type, ProductionImage image, String actionBy) {
        return kafkaTemplate.send(logTopic, logMessageOf(type, image, actionBy));
    }

    private LogMessage logMessageOf(@NonNull ActionType type,
                                    @NonNull ProfileImage image,
                                    @Nullable String actionBy) {
        return LogMessage.builder()
                .actionType(type.name())
                .actionBy(actionBy)
                .actionData(image)
                .timeMs(System.currentTimeMillis())
                .build();
    }

    private LogMessage logMessageOf(@NonNull ActionType type,
                                    @NonNull ProductionImage image,
                                    @Nullable String actionBy) {
        return LogMessage.builder()
                .actionType(type.name())
                .actionBy(actionBy)
                .actionData(image)
                .timeMs(System.currentTimeMillis())
                .build();
    }

}
