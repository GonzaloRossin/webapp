package com.aquastilo.webapp.controller;

import com.aquastilo.webapp.dto.ImageDto;
import com.aquastilo.webapp.interfaces.service.ImageService;
import com.aquastilo.webapp.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/resources/images")
public class ImageController {
    private final ImageService is;

    @Autowired
    public ImageController(ImageService is) {
        this.is = is;
    }
    @GetMapping(path="/{imageId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getImage(@PathVariable String imageId){
        long id = Long.parseLong(imageId);

        Optional<Image> imageOptional = is.getImage(id);
        if(imageOptional.isPresent()){
            ImageDto imageDto = ImageDto.fromImage(imageOptional.get());

            return ResponseEntity
                    .ok()
                    .body(imageDto.getBitMap());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Long> postImage(@RequestParam("image") MultipartFile file){
        long imageId = is.createImage(file);
        return new ResponseEntity<>(imageId, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id){
        is.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
