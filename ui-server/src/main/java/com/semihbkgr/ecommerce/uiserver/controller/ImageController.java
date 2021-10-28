package com.semihbkgr.ecommerce.uiserver.controller;

import com.semihbkgr.ecommerce.uiserver.client.ImageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageClient imageClient;

    @GetMapping(path = "/user/{username}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public byte[] userImage(@PathVariable("username")String username){
        return imageClient.getProfileContent(username);
    }

    @GetMapping(path="/production/{image-id}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public byte[] productionImage(@PathVariable("image-id") String imageId){
        return imageClient.getProductionContent(imageId);
    }

}
