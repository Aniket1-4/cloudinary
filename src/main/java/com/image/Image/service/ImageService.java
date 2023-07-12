package com.image.Image.service;

import com.image.Image.dto.ImageDTO;
import com.image.Image.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String uploadFile(MultipartFile multipartFile) throws IOException;

    void saveLink(String imageUrl, String type);

    ImageDTO findById(int id);
}