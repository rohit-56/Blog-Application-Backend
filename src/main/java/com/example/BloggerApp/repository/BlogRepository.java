package com.example.BloggerApp.repository;

import com.example.BloggerApp.models.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity,Long> {
    BlogEntity getById(long id);

    void deleteById(long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BLOG_ENTITY b SET b.id = ?1 WHERE b.id = ?2",nativeQuery = true)
    void updateBlogEntity(long id,long blog_id);
}
