package com.aquastilo.webapp.interfaces.service;

import com.aquastilo.webapp.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ImageService {

    Optional<Image> getImage(long id);

    long createImage(MultipartFile image);

    void deleteImage(long id);
}
