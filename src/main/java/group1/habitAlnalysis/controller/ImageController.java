package group1.habitAlnalysis.controller;

import group1.habitAlnalysis.entity.ImageEntity;
import group1.habitAlnalysis.repository.ImageRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin(origins = "https://drive.google.com")
public class ImageController {
    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping("/api/get/images")
    public List<ImageEntity> getImage() {
        return this.imageRepository.findAll();
    }
}
