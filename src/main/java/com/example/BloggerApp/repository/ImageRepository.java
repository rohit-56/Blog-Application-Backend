package com.example.BloggerApp.repository;

import com.example.BloggerApp.models.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
}
