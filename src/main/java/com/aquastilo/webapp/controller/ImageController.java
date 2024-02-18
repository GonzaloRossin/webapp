package com.aquastilo.webapp.controller;

import com.aquastilo.webapp.dto.ImageDto;
import com.aquastilo.webapp.interfaces.service.ImageService;
import com.aquastilo.webapp.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "api/v1/resources/images")
public class ImageController {
    private final ImageService is;

    @Autowired
    public ImageController(ImageService is) {
        this.is = is;
    }

    @GetMapping(path="/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable String imageId){
        long id = Long.parseLong(imageId);

        Image image = is.getImage(id);
        ImageDto imageDto = ImageDto.fromImage(image);

        return ResponseEntity.ok()
                        .contentType(MediaType.valueOf("image/jpeg"))
                        .body(imageDto.getBitMap());
    }

    @PostMapping
    public ResponseEntity<?> postImage(@RequestParam("image") MultipartFile file){
        long imageId = is.createImage(file);
        return ResponseEntity.ok(imageId);
    }
}
