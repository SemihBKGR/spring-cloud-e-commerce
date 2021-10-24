package com.semihbkgr.ecommerce.productionserver.component;

import com.semihbkgr.ecommerce.modelcommon.production.Production;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

public interface KafkaProductionLogSender {

    enum ProductionActionType{
        CREATE,
        UPDATE,
        DELETE
    }

    Mono<SenderResult<Void>> log(ProductionActionType type, Production production);

    Mono<SenderResult<Void>> log(ProductionActionType type, Production production,String actionBy);

}
