package com.image.Image.exception;

import org.springframework.http.HttpStatus;

public class NoImageFoundException extends RuntimeException{
    public NoImageFoundException(String imageNotFound, HttpStatus ok) {
    }
}
