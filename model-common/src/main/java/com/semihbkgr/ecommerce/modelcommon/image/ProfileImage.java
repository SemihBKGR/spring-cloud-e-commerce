package com.semihbkgr.ecommerce.modelcommon.image;

import com.semihbkgr.ecommerce.modelcommon.auditable.PersonAuditable;
import com.semihbkgr.ecommerce.modelcommon.auditable.TimeAuditable;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Document("profile_images")
public class ProfileImage implements PersonAuditable, TimeAuditable {

    @Id
    private String id;

    private String extension;
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
