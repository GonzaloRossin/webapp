package com.aquastilo.webapp.interfaces.service;

import com.aquastilo.webapp.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image getImage(long id);

    long createImage(MultipartFile image);
}
