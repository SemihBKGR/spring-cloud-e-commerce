package com.semihbkgr.ecommerce.modelcommon.message;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class LogMessage {

    private String actionType;
    private String actionBy;
    private Object actionData;
    private long timeMs;

}
