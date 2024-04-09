package com.example.BloggerApp.service.impl;

import com.example.BloggerApp.common.utils.ImageUtils;
import com.example.BloggerApp.models.ImageEntity;
import com.example.BloggerApp.repository.ImageRepository;
import com.example.BloggerApp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Override
    public ImageEntity uploadImage(MultipartFile file) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setName(file.getName());
        imageEntity.setType(file.getContentType());
        try {
            imageEntity.setImageData(ImageUtils.compressImage(file.getBytes()));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return imageRepository.save(imageEntity);
    }

    @Override
    public ImageEntity downloadImage(Long imageId) {
        return imageRepository.findById(imageId).get();
    }
}
