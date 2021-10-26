package com.semihbkgr.ecommerce.modelcommon.image;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Image {

    private String id;
    private String name;
    private long size;
    private int width;
    private int height;

}
