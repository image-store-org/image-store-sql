package com.vartdalen.imagestoresql.service;
import com.vartdalen.imagestoresql.model.Image;
import com.vartdalen.imagestoresql.repository.ImageRepository;
import com.vartdalen.imagestoresql.util.ReflectionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> get() {
        return imageRepository.findAll();
    }

    public Image get(long id) {
        Optional<Image> match = imageRepository.findById(id);
        if (match.isEmpty()) {
            throw new EntityNotFoundException(String.format("Image with id %s was not found", id));
        }
        return match.get();
    }

    public Image post(Image image) {
        return imageRepository.save(image);
    }

    public Image put(Image image) {
        Optional<Image> match = imageRepository.findById(image.getId());
        if (match.isEmpty()) {
            throw new EntityNotFoundException(String.format("Image with id %s was not found", image.getId()));
        }
        Image merged;
        try {
            merged = ReflectionUtil.mergeObjects(match.get(), image);
        } catch (IllegalAccessException e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Unable to merge Images with id %s", image.getId()));
        }
        return imageRepository.save(merged);
    }

    public void delete(long id) {
        imageRepository.deleteById(id);
    }
}
