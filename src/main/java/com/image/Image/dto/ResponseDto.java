package com.image.Image.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {

    private String status;
   // private String message;
    private ImageDTO imageDTO;

    public ResponseDto(String status,ImageDTO imageDTO){
        this.status=status;
        this.imageDTO=imageDTO;
    }
}
