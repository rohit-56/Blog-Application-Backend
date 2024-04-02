package com.example.BloggerApp.repository;

import com.example.BloggerApp.models.BlogEntity;
import com.example.BloggerApp.models.CategoryEntity;
import com.example.BloggerApp.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity,Long> {
    BlogEntity getById(long id);

    BlogEntity deleteById(long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE BLOG_ENTITY b SET b.id = ?1 WHERE b.id = ?2",nativeQuery = true)
    void updateBlogEntity(long id,long blog_id);

    List<BlogEntity> getBlogByCategoryEntity(CategoryEntity categoryEntity);

    List<BlogEntity> getBlogByUserEntity(UserEntity userEntity);

    @Query(value = "SELECT mywork.blog_entity.id,mywork.blog_entity.title, mywork.category_entity.title as category,mywork.blog_entity.body,mywork.blog_entity.created_at,mywork.blog_entity.image_cover as cover,mywork.user_entity.image,mywork.user_entity.username from mywork.blog_entity inner join mywork.user_entity on mywork.blog_entity.user_entity_id=mywork.user_entity.id inner join mywork.category_entity on mywork.blog_entity.category_entity_id=mywork.category_entity.id ORDER BY mywork.blog_entity.id limit ?1 offset ?2 ;",
           countQuery = "select count(*) from mywork.blog_entity;",nativeQuery = true)
    List<Map<String,Object>> findAllBlogEntitywithUserDetails(long limit,long offset);
}
