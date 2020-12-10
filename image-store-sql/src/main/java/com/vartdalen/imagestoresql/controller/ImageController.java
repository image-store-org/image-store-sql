package com.vartdalen.imagestoresql.controller;
import com.vartdalen.imagestoresql.model.Image;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vartdalen.imagestoresql.service.ImageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(
            value = "/helloWorld",
            method = RequestMethod.GET
    )
    public String helloWorld(){ return imageService.helloWorld(); }

    @ResponseBody
    @GetMapping("/")
    public List<Image> getAllImages() { return imageService.getImages(); }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable String id) throws NotFoundException {
        long parsedId = Long.parseLong(id);
        Optional<Image> match = imageService.getImageById(parsedId);
        if (match.isEmpty()) {
            String message = String.format("Image with id %s was not found.", id);
            throw new NotFoundException(message);
        }
        return new ResponseEntity<>(match.get(), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/")
    public ResponseEntity<Image> saveImage(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.saveImage(image), HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable long id, @RequestBody Image image) {
        image.setId(id);
        return new ResponseEntity<>(imageService.saveImage(image), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable long id) {
        imageService.deleteImageById(id);
    }
}
