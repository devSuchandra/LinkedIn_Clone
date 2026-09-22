package com.CodingShuttle.LinkedIn.PostService.Repository;

import com.CodingShuttle.LinkedIn.PostService.Entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
}
