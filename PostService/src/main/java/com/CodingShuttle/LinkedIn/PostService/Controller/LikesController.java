package com.CodingShuttle.LinkedIn.PostService.Controller;

import com.CodingShuttle.LinkedIn.PostService.Service.PostLikeService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/likes/{postId}")
    public ResponseEntity<Void> likePost(
            @PathVariable @Positive(message = "Post ID must be greater than zero") Long postId) {
        postLikeService.likePost(postId, 1L); // Assuming userId is 1L for now
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/unlikes/{postId}")
    public ResponseEntity<Void> unlikePost(
            @PathVariable @Positive(message = "Post ID must be greater than zero") Long postId) {
        postLikeService.unlikePost(postId, 1L); // Assuming userId is 1L for now
        return ResponseEntity.noContent().build();
    }

}
