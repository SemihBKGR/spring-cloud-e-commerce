package com.semihbkgr.ecommerce.modelcommon.image;

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
@Document("production_images")
public class ProductionImage implements PersonAuditable, TimeAuditable {

    @Id
    private String id;

    @Indexed
    private String productionId;
    private int displayOrder;
    private String extension;
    private long size;

    @CreatedDate
    private long createdAt;

    @LastModifiedDate
    private long updatedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

}
