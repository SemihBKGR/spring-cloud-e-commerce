package com.semihbkgr.ecommerce.modelcommon.production;

import com.semihbkgr.ecommerce.modelcommon.auditable.PersonAuditable;
import com.semihbkgr.ecommerce.modelcommon.auditable.TimeAuditable;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Document("productions")
public class Production implements PersonAuditable, TimeAuditable {

    @Id
    private String id;

    @Indexed
    private String owner;
    private String name;
    private float price;
    private int stock;
    private String description;

    @CreatedDate
    private long createdAt;

    @LastModifiedDate
    private long updatedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

}
