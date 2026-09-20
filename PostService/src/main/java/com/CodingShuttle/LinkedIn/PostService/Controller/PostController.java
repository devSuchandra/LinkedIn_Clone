package com.CodingShuttle.LinkedIn.PostService.Controller;

import com.CodingShuttle.LinkedIn.PostService.DTO.PostCreateRequestDto;
import com.CodingShuttle.LinkedIn.PostService.DTO.PostDto;
import com.CodingShuttle.LinkedIn.PostService.Entity.Post;
import com.CodingShuttle.LinkedIn.PostService.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest) {
        // Logic to create a post
        PostDto createdPost = postService.createPost(postCreateRequestDto, 1L); // Assuming userId is 1 for demonstration purposes
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        // Logic to get a post by ID
        PostDto postDto = postService.getPostById(postId);
        if (postDto != null) {
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
