package com.image.Image.dto;

import com.image.Image.model.Image;
import lombok.Data;

import java.util.Optional;

@Data
public class ImageDTO {
    private String imageLink;
    private String type;

    public ImageDTO(String imageLink, String type) {
        this.imageLink = imageLink;
        this.type = type;
    }


}
