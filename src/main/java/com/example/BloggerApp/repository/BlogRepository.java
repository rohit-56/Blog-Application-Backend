package com.example.BloggerApp.repository;

import com.example.BloggerApp.models.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity,Long> {

}
