package com.CodingShuttle.LinkedIn.PostService.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Post content must not be blank")
    @Size(max = 5000, message = "Post content must not exceed 5000 characters")
    @Column(nullable = false, length = 5000)
    private String content;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be greater than zero")
    @Column(nullable = false)
    private Long userId;

    @CreationTimestamp
    @PastOrPresent(message = "Post creation time cannot be in the future")
    private LocalDateTime createdAt;
}
