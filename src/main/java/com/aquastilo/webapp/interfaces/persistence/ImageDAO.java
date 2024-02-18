package com.aquastilo.webapp.interfaces.persistence;

import com.aquastilo.webapp.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageDAO {

    Optional<Image> getImageById(long id);

    long uploadImage(MultipartFile imageFile) throws IOException;
}
