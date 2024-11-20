package com.microservice.comment.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String postId;
    private String title;
    private String description;
    private String content;
}