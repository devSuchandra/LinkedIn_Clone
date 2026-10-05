package com.CodingShuttle.LinkedIn.PostService.Service;

import com.CodingShuttle.LinkedIn.PostService.Entity.PostLike;
import com.CodingShuttle.LinkedIn.PostService.Exception.BadRequestException;
import com.CodingShuttle.LinkedIn.PostService.Exception.ResourceNotFoundException;
import com.CodingShuttle.LinkedIn.PostService.Repository.PostLikeRepository;
import com.CodingShuttle.LinkedIn.PostService.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public void likePost(Long postId, Long userId) {
        validateIds(postId, userId);
        log.info("User with ID {} is attempting to like post", userId);

        boolean exists;
        try {
            exists = postRepository.existsById(postId);
        } catch (RuntimeException exception) {
            log.error("Failed to check whether post with ID {} exists while liking", postId, exception);
            throw exception;
        }
        if (!exists) {
            log.error("Post with ID {} not found for liking", postId);
            throw new ResourceNotFoundException("Post not found with ID: " + postId);
        }

        boolean alreadyLiked;
        try {
            alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        } catch (RuntimeException exception) {
            log.error("Failed to check whether user with ID {} liked post with ID {}", userId, postId, exception);
            throw exception;
        }
        if (alreadyLiked) {
            log.warn("User with ID {} has already liked post with ID {}", userId, postId);
            throw new BadRequestException("User has already liked this post");
        }

        PostLike postLike = new PostLike();
        postLike.setUserId(userId);
        postLike.setPostId(postId);
    }

    private void validateIds(Long postId, Long userId) {
        if (postId == null || postId <= 0) {
            throw new BadRequestException("Post ID must be greater than zero");
        }
        if (userId == null || userId <= 0) {
            throw new BadRequestException("User ID must be greater than zero");
        }
    }
}
