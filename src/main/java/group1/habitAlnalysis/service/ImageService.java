package group1.habitAlnalysis.service;

import group1.habitAlnalysis.entity.ImageEntity;
import group1.habitAlnalysis.repository.ImageRepository;
import group1.habitAlnalysis.util.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        ImageEntity imageEntity = imageRepository.save(ImageEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        if(imageEntity != null) {
            return  "File upload successful: "+file.getOriginalFilename();
        }
        return null;
    }

    public  byte[] downloadImage(String filename){
        Optional<ImageEntity> dbImage = imageRepository.findByName(filename);
        byte[] images = ImageUtils.decompressImage(dbImage.get().getImageData());
        return images;
    }
}
