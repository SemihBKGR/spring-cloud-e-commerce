package com.semihbkgr.ecommerce.imageserver.api;

import com.semihbkgr.ecommerce.imageserver.client.ProductionClient;
import com.semihbkgr.ecommerce.imageserver.component.KafkaImageLogSender;
import com.semihbkgr.ecommerce.imageserver.content.ImageContentProvider;
import com.semihbkgr.ecommerce.imageserver.service.ProductionImageService;
import com.semihbkgr.ecommerce.imageserver.util.FilenameUtils;
import com.semihbkgr.ecommerce.imageserver.util.PrincipalUtils;
import com.semihbkgr.ecommerce.modelcommon.image.ProductionImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/production")
public class ProductionImageApi {

    private final ProductionImageService productionImageService;
    private final ProductionClient productionClient;
    private final KafkaImageLogSender kafkaImageLogSender;
    private final ImageContentProvider imageContentProvider;

    @Autowired
    public ProductionImageApi(ProductionImageService productionImageService,
                              ProductionClient productionClient,
                              KafkaImageLogSender kafkaImageLogSender,
                              @Qualifier("productionImageContentProvider") ImageContentProvider imageContentProvider) {
        this.productionImageService = productionImageService;
        this.productionClient = productionClient;
        this.kafkaImageLogSender = kafkaImageLogSender;
        this.imageContentProvider = imageContentProvider;
    }

    @GetMapping(path = "/{image-id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductionImage get(@PathVariable("image-id") String imageId) {
        return productionImageService.findById(imageId);
    }

    @GetMapping("/all/{production-id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductionImage> getAll(@PathVariable("production-id") String productionId) {
        var production = productionClient.findById(productionId);
        if (production == null)
            throw new IllegalStateException();
        return productionImageService.findAllByProductionId(productionId);
    }

    @GetMapping(path = "/content/{image-id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getContent(@PathVariable("image-id") String imageId) throws IOException {
        var image = productionImageService.findById(imageId);
        if (image == null)
            return new byte[0];
        return imageContentProvider.get(image.getProductionId(),
                FilenameUtils.attachExtension(image.getId(), image.getExtension()));
    }

    @PostMapping("/{production-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductionImage upload(@PathVariable("production-id") String productionId,
                                  @RequestParam("content") MultipartFile file,
                                  @RequestParam(value = "displayOrder", required = false, defaultValue = "0") int displayOrder,
                                  HttpServletRequest request) throws IOException {
        var production = productionClient.findById(productionId);
        if (production == null)
            throw new IllegalStateException();
        var username = PrincipalUtils.getUsername(request);
        if (!production.getOwner().equals(username))
            throw new IllegalStateException();
        var image = ProductionImage.builder()
                .productionId(production.getId())
                .displayOrder(displayOrder)
                .extension(FilenameUtils.detachExtension(file.getOriginalFilename()))
                .size(file.getSize())
                .build();
        var imageFromDb = productionImageService.save(image);
        imageContentProvider.save(imageFromDb.getProductionId(),
                FilenameUtils.attachExtension(imageFromDb.getId(), imageFromDb.getExtension()), file);
        kafkaImageLogSender.log(KafkaImageLogSender.ActionType.UPLOAD, imageFromDb, username);
        return imageFromDb;
    }

    @DeleteMapping("/{image-id}")
    public ProductionImage delete(@PathVariable("image-id") String imageId,
                                  HttpServletRequest request) throws IOException {
        var image = productionImageService.findById(imageId);
        if (image == null)
            throw new IllegalStateException();
        var production = productionClient.findById(image.getProductionId());
        if (production == null)
            throw new IllegalStateException();
        var username = PrincipalUtils.getUsername(request);
        if (!production.getOwner().equals(username))
            throw new IllegalStateException();
        productionImageService.deleteById(image.getId());
        imageContentProvider.delete(image.getProductionId(),
                FilenameUtils.attachExtension(image.getId(), image.getExtension()));
        kafkaImageLogSender.log(KafkaImageLogSender.ActionType.DELETE, image, username);
        return image;
    }

    @DeleteMapping("/all/{production-id}")
    public List<ProductionImage> deleteAll(@PathVariable("production-id") String productionId,
                                           HttpServletRequest request) throws IOException {
        var production = productionClient.findById(productionId);
        if (production == null)
            throw new IllegalStateException();
        var username = PrincipalUtils.getUsername(request);
        if (!production.getOwner().equals(username))
            throw new IllegalStateException();
        var imageList = productionImageService.findAllByProductionId(productionId);
        for (var image : imageList) {
            productionImageService.deleteById(image.getId());
            imageContentProvider.delete(image.getProductionId(),
                    FilenameUtils.attachExtension(image.getId(), image.getExtension()));
            kafkaImageLogSender.log(KafkaImageLogSender.ActionType.DELETE, image, username);
        }
        return imageList;
    }

}
