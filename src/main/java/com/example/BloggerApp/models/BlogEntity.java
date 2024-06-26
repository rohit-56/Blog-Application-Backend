package com.example.BloggerApp.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "blog_id")
    private List<TagEntity> tagEntities;

    @NonNull
    private String body;

    private LocalDateTime createdAt;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imageCover;

    @ManyToOne
    private CategoryEntity categoryEntity;

    @ManyToOne
    private UserEntity userEntity;

    @OneToMany(mappedBy = "blogEntity",cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

}
