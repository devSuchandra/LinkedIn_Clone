package com.CodingShuttle.LinkedIn.PostService.Repository;

import com.CodingShuttle.LinkedIn.PostService.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
