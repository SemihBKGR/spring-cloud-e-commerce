package com.semihbkgr.ecommerce.imageserver.component;

import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import com.semihbkgr.ecommerce.modelcommon.production.Production;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

public interface KafkaImageLogSender {

    enum ActionType {
        CREATE,
        UPDATE,
        DELETE
    }

    Mono<SenderResult<Void>> log(ActionType type, ProfileImage image, String actionBy);

    Mono<SenderResult<Void>> log(ActionType type, ProductionImage image, String actionBy);

}
