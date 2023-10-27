package com.example.BloggerApp.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String subtitle;

    @OneToMany(targetEntity = TagEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    private List<TagEntity> tagEntities;

    @NonNull
    private String body;

    private LocalDateTime createdAt;

    @NonNull
    private String imageCover;



}
