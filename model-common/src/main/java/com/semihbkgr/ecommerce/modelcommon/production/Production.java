package com.semihbkgr.ecommerce.modelcommon.production;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Production {

    @Id
    private String id;
    private String owner;
    private String name;
    private float price;
    private int stock;
    private String description;

}
