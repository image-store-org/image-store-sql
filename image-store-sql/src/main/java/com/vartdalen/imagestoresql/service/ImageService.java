package com.vartdalen.imagestoresql.service;
import com.vartdalen.imagestoresql.model.Image;
import com.vartdalen.imagestoresql.repository.ImageRepository;
import com.vartdalen.imagestoresql.validation.QueryValidation;
import org.springframework.stereotype.Service;
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
        Image validated = QueryValidation.put(image, imageRepository);
        return imageRepository.save(validated);
    }

    public void delete(long id) {
        imageRepository.deleteById(id);
    }
}
