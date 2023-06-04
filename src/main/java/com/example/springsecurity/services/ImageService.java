package com.example.springsecurity.services;

import com.example.springsecurity.models.Image;
import com.example.springsecurity.models.Product;
import com.example.springsecurity.repositories.ImageRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (readOnly = true)
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public void deleteImageByProductId (int id){
        imageRepository.deleteImageByProductId(id);
    }
    public List<Image> findAllImageForProductId (int id){
        return imageRepository.findAllImageForProductId(id);
    }
}
