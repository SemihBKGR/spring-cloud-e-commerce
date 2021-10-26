package com.semihbkgr.ecommerce.modelcommon.production;

import com.semihbkgr.ecommerce.modelcommon.auditable.PersonAuditable;
import com.semihbkgr.ecommerce.modelcommon.auditable.TimeAuditable;
import lombok.*;
import org.springframework.data.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Production implements PersonAuditable, TimeAuditable {

    @Id
    private String id;
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
