package com.semihbkgr.ecommerce.imageserver.api;

import com.semihbkgr.ecommerce.imageserver.content.ImageContentProvider;
import com.semihbkgr.ecommerce.imageserver.service.ProfileImageService;
import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/profile")
public class ProfileImageApi {

    private final ProfileImageService profileImageService;
    private final ImageContentProvider imageContentProvider;

    @Autowired
    public ProfileImageApi(ProfileImageService profileImageService,
                           @Qualifier("profileImageContentProvider") ImageContentProvider imageContentProvider) {
        this.profileImageService = profileImageService;
        this.imageContentProvider = imageContentProvider;
    }

    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProfileImage> save(@RequestPart("content") Mono<FilePart> filePartMono,
                                   @RequestHeader("Content-Length") long length,
                                   ServerWebExchange exchange) {
        return exchange.<KeycloakPrincipal>getPrincipal().flatMap(principal -> {
            var session = principal.getKeycloakSecurityContext();
            var accessToken = session.getToken();
            var username = accessToken.getPreferredUsername();
            var image = ProfileImage.builder()
                    .id(username)
                    .size(length)
                    .build();
            return profileImageService.save(image)
                    .flatMap(savedImage -> filePartMono
                            .flatMap(content ->
                                    imageContentProvider.save(savedImage.getId(), content.content())
                                            .thenReturn(savedImage)
                            ));
        });
    }

    */

    @PostMapping(consumes = "image/jpeg")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProfileImage> save(@RequestPart("content") Mono<FilePart> filePartMono) {
        var image = ProfileImage.builder()
                .id("test")
                .build();
        return profileImageService.save(image)
                .flatMap(savedImage -> filePartMono
                        .flatMap(content ->
                                imageContentProvider.save(savedImage.getId(), content.content())
                                        .thenReturn(savedImage)
                        ));
    }


    @GetMapping(value = "/{user-id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Flux<DataBuffer> get(@PathVariable("user-id") String userId) {
        return imageContentProvider.get(userId);
    }


}
