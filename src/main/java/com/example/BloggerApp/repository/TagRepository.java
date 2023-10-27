package com.example.BloggerApp.repository;

import com.example.BloggerApp.models.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity,Long> {
}
