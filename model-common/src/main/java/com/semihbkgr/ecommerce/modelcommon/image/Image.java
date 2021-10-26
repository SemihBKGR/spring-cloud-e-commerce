package com.semihbkgr.ecommerce.modelcommon.image;

import com.semihbkgr.ecommerce.modelcommon.auditable.PersonAuditable;
import com.semihbkgr.ecommerce.modelcommon.auditable.TimeAuditable;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Image implements PersonAuditable, TimeAuditable {

    private String id;
    private String name;
    private long size;
    private int width;
    private int height;

    @CreatedDate
    private long createdAt;

    @LastModifiedDate
    private long updatedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

}
