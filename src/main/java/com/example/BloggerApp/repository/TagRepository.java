package com.example.BloggerApp.repository;

import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity,Long> {

}
