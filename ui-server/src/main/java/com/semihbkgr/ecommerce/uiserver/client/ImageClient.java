package com.semihbkgr.ecommerce.uiserver.client;

import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient("IMAGE")
public interface ImageClient {

    @GetMapping("/profile/{username}")
    ProfileImage getProfile(@PathVariable("username") String username);

    @GetMapping(value = "/profile/content/{username}")
    byte[] getProfileContent(@PathVariable("username") String username);

    @PostMapping("/profile")
    ProfileImage uploadProfile(@RequestParam("content") MultipartFile file,
                               @RequestHeader("Authorization") String authorization);

    @DeleteMapping("/profile")
    ProfileImage deleteProfile(@RequestHeader("Authorization") String authorization);

    @GetMapping("/production/{image-id}")
    ProductionImage getProduction(@PathVariable("image-id") String imageId);

    @GetMapping("/production/all/{production-id}")
    List<ProductionImage> getAllProductions(@PathVariable("production-id") String productionId);

    @GetMapping("/production/content/{image-id}")
    byte[] getProductionContent(@PathVariable("image-id") String imageId);

    @PostMapping("/production/{production-id}")
    ProductionImage uploadProduction(@PathVariable("production-id") String productionId,
                                     @RequestParam("content") MultipartFile file,
                                     @RequestHeader("Authorization") String authorization);

    @DeleteMapping("/production/{image-id}")
    ProductionImage deleteProduction(@PathVariable("image-id") String imageId,
                                     @RequestHeader("Authorization") String authorization);

    @DeleteMapping("/production/all/{production-id}")
    List<ProductionImage> deleteAllProductions(@PathVariable("production-id") String productionId,
                                               @RequestHeader("Authorization") String authorization);

}
