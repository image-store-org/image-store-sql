package com.vartdalen.imagestoresql.controller;
import com.vartdalen.imagestoresql.model.Image;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vartdalen.imagestoresql.service.ImageService;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ResponseBody
    @GetMapping("")
    public ResponseEntity<List<Image>> get() { return new ResponseEntity<>(imageService.get(), HttpStatus.OK); }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Image> get(@PathVariable long id) {
        return new ResponseEntity<>(imageService.get(id), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<Image> post(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.post(image), HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<Image> put(@PathVariable long id, @RequestBody Image image) {
        image.setId(id);
        return new ResponseEntity<>(imageService.put(image), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
