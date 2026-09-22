package com.CodingShuttle.LinkedIn.PostService.Service;

import com.CodingShuttle.LinkedIn.PostService.DTO.PostCreateRequestDto;
import com.CodingShuttle.LinkedIn.PostService.DTO.PostDto;
import com.CodingShuttle.LinkedIn.PostService.Entity.Post;
import com.CodingShuttle.LinkedIn.PostService.Exception.BadRequestException;
import com.CodingShuttle.LinkedIn.PostService.Exception.ResourceNotFoundException;
import com.CodingShuttle.LinkedIn.PostService.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId){
        // Here you would typically save the post to the database using postRepository
        // For demonstration, we'll just map the DTO to a PostDto and return it
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.debug("Retrieving post with ID: {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + postId));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        validateUserId(userId);
        log.debug("Retrieving all posts for user with ID: {}", userId);
        List<Post> posts = postRepository.findByUserId(userId);

        return posts.stream()
                .map((element) -> modelMapper.map(element, PostDto.class))
                .collect(Collectors.toList());
    }

    private void validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new BadRequestException("User ID must be greater than zero");
        }
    }
}
