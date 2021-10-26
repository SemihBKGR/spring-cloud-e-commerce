package com.semihbkgr.ecommerce.imageserver.component;

import com.semihbkgr.ecommerce.modelcommon.image.Image;
import com.semihbkgr.ecommerce.modelcommon.production.Production;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

public interface KafkaImageLogSender {

    enum ActionType {
        CREATE,
        UPDATE,
        DELETE
    }

    Mono<SenderResult<Void>> log(ActionType type, Image image);

    Mono<SenderResult<Void>> log(ActionType type, Image image, String actionBy);

}
