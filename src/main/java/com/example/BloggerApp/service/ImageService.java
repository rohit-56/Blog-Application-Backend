package com.example.BloggerApp.service;

import com.example.BloggerApp.models.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageEntity uploadImage(MultipartFile file);

    ImageEntity downloadImage(Long imageId);
}
