package com.aquastilo.webapp.service;

import com.aquastilo.webapp.interfaces.persistence.ImageDAO;
import com.aquastilo.webapp.interfaces.service.ImageService;
import com.aquastilo.webapp.model.Image;
import com.aquastilo.webapp.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;

    @Autowired
    public ImageServiceImpl(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Override
    public Image getImage(long id) {
        Optional<Image> imageOptional = imageDAO.getImageById(id);
        return imageOptional.orElse(null);
    }

    @Transactional
    @Override
    public long createImage(MultipartFile image) {

        try {
            return imageDAO.uploadImage(image);
        } catch (IOException exception) {
            return -1;
        }
    }
}
