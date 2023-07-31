package group1.habitAlnalysis.controller;
import group1.habitAlnalysis.repository.ImageRepository;
import group1.habitAlnalysis.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@CrossOrigin(origins = "https://drive.google.com")
public class ImageController {
    private final ImageService service;

    public ImageController(ImageRepository imageRepository, ImageService service) {
        this.service = service;
    }
//    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/upload/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/api/download/image")
    public  ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] image = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
