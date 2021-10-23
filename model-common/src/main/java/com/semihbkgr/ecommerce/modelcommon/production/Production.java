package com.semihbkgr.ecommerce.modelcommon.production;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Production {

    private String id;
    private String ownerId;
    private String name;
    private float price;

}
