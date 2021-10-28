package com.semihbkgr.ecommerce.imageserver.component;

import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public interface KafkaImageLogSender {

    ListenableFuture<SendResult<Object, Object>> log(ActionType type, ProfileImage image, String actionBy);

    ListenableFuture<SendResult<Object, Object>> log(ActionType type, ProductionImage image, String actionBy);

    enum ActionType {
        UPLOAD,
        DELETE
    }

}
