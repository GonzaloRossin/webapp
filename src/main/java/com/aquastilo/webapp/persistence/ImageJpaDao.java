package com.aquastilo.webapp.persistence;

import com.aquastilo.webapp.interfaces.persistence.ImageDAO;
import com.aquastilo.webapp.model.Image;
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

        Image image = new Image(imageFile.getBytes(), imageFile.getContentType());
        em.persist(image);
        return image.getId();

    }

    @Override
    public void deleteImage(Image image) {
        em.remove(image);
    }

    @Override
    public void updateImage(long id, MultipartFile imageFile) throws IOException {
        Image image = em.find(Image.class, id);
        image.setBytes(imageFile.getBytes());
        em.merge(image);
    }
}
