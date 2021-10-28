package com.semihbkgr.ecommerce.imageserver.api;

import com.semihbkgr.ecommerce.imageserver.component.KafkaImageLogSender;
import com.semihbkgr.ecommerce.imageserver.content.ImageContentProvider;
import com.semihbkgr.ecommerce.imageserver.service.ProfileImageService;
import com.semihbkgr.ecommerce.imageserver.util.FilenameUtils;
import com.semihbkgr.ecommerce.imageserver.util.PrincipalUtils;
import com.semihbkgr.ecommerce.modelcommon.image.ProfileImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/profile")
public class ProfileImageApi {

    private final ProfileImageService profileImageService;
    private final KafkaImageLogSender kafkaImageLogSender;
    private final ImageContentProvider imageContentProvider;


    @Autowired
    public ProfileImageApi(ProfileImageService profileImageService,
                           KafkaImageLogSender kafkaImageLogSender,
                           @Qualifier("profileImageContentProvider") ImageContentProvider imageContentProvider) {
        this.profileImageService = profileImageService;
        this.kafkaImageLogSender = kafkaImageLogSender;
        this.imageContentProvider = imageContentProvider;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileImage load(@RequestParam("content") MultipartFile file, HttpServletRequest request) throws IOException {
        var username = PrincipalUtils.getUsername(request);
        var extension = FilenameUtils.extractExtension(file.getOriginalFilename());
        var image = ProfileImage.builder()
                .id(username)
                .extension(extension)
                .size(file.getSize())
                .build();
        var imageFromDb = profileImageService.save(image);
        imageContentProvider.save(username, file);
        kafkaImageLogSender.log(KafkaImageLogSender.ProfileActionType.LOAD, imageFromDb, username);
        return imageFromDb;
    }

    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public byte[] get(HttpServletRequest request) throws IOException {
        var username = PrincipalUtils.getUsername(request);
        var image = profileImageService.findById(username);
        if (image == null)
            return new byte[0];
        return imageContentProvider.get(username);
    }

    @GetMapping(value = "/{username}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public byte[] get(@PathVariable("username") String username) throws IOException {
        var image = profileImageService.findById(username);
        if (image == null)
            return new byte[0];
        return imageContentProvider.get(username);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProfileImage delete(HttpServletRequest request) throws IOException {
        var username = PrincipalUtils.getUsername(request);
        var image = profileImageService.deleteById(username);
        if (image == null)
            return null;
        imageContentProvider.delete(username);
        kafkaImageLogSender.log(KafkaImageLogSender.ProfileActionType.DELETE, image, username);
        return image;
    }

}

