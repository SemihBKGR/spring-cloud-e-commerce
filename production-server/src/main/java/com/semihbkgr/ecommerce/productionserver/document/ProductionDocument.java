package com.semihbkgr.ecommerce.productionserver.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Document("productions")
public class ProductionDocument {

    @Id
    private String id;
    private String ownerId;
    private String name;
    private float price;
    private int stock;
    private String description;

}
