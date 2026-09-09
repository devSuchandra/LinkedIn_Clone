package com.CodingShuttle.LinkedIn.PostService.DTO;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String content;
    private Long userId;
    private String createdAt;
}
