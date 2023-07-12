package com.image.Image.controller;

import com.image.Image.dto.ImageDTO;
import com.image.Image.dto.ResponseDto;
import com.image.Image.exception.NoImageFoundException;
import com.image.Image.service.ImageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    @Autowired
    private final ImageService imageService;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile,@RequestParam String type) throws IOException {
        String imageUrl = imageService.uploadFile(multipartFile);
        imageService.saveLink(imageUrl,type);

        return "image saved";
    }

    @GetMapping("/getImage")
    public ResponseEntity<?> getImage(@RequestParam int id){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Successful",imageService.findById(id)));

        }catch (NoImageFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("Unsuccessful",null));
        }
    }
}