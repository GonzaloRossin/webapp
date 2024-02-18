package com.aquastilo.webapp.persistence;

import com.aquastilo.webapp.interfaces.persistence.ImageDAO;
import com.aquastilo.webapp.model.Image;
import com.aquastilo.webapp.utils.ImageUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Repository
public class ImageJpaDao implements ImageDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Image> getImageById(long id) {
        return Optional.ofNullable(em.find(Image.class, id));
    }


    @Override
    public long uploadImage(MultipartFile imageFile) throws IOException {
        //byte[] imageData = ImageUtils.compressImage(imageFile.getBytes());

        Image image = new Image(imageFile.getBytes());
        em.persist(image);
        return image.getId();

    }
}
