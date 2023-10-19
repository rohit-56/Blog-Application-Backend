package com.example.BloggerApp.repository;

import com.example.BloggerApp.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getById(Long id);

    int deleteById(int id);

    @Transactional
    @Modifying
    @Query(value = "update UserEntity u set u.username=:username,u.email=:email,u.image=:image,u.bio=:bio where u.id=:id")
    void updateUser(@Param("username") String username, @Param("email") String email, @Param("image") String image, @Param("bio") String bio, @Param("id") Long id);

}
