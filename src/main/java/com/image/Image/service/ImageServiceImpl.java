package com.image.Image.service;

import com.cloudinary.Cloudinary;
import com.image.Image.dto.ImageDTO;
import com.image.Image.exception.NoImageFoundException;
import com.image.Image.model.Image;
import com.image.Image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Autowired
    private final Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }

    @Override
    public void saveLink(String imageUrl, String type) {
        Image image = new Image();
        image.setImageLink(imageUrl);
        image.setType(type);

        imageRepository.save(image);

    }

    @Override
    public ImageDTO findById(int id) {
        Optional<Image> optionalImage = imageRepository.findById(id);
        if (optionalImage.isPresent()) {
            String link = optionalImage.get().getImageLink();
            String type = optionalImage.get().getType();
            return new ImageDTO(link,type);
        }else {
            throw  new NoImageFoundException("Image not found", HttpStatus.NOT_FOUND);
        }
    }
}