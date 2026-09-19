package com.CodingShuttle.LinkedIn.PostService.Controller;

import com.CodingShuttle.LinkedIn.PostService.Service.PostLikeService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
@Validated
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(
            @PathVariable @Positive(message = "Post ID must be greater than zero") Long postId) {
        postLikeService.likePost(postId, 1L); // Assuming userId is 1L for now
        return ResponseEntity.noContent().build();
    }

}
