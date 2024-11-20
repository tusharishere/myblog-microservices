package com.microservice.post.service;

import com.microservice.post.config.RestTemplateConfig;
import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.repository.PostRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostService {

    private PostRepository postRepository;
    private RestTemplateConfig restTemplate;

    public PostService(PostRepository postRepository, RestTemplateConfig restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }

    public Post savePost(Post post) {
        String randomPostId = UUID.randomUUID().toString();
        post.setId(randomPostId);
        return postRepository.save(post);
    }

    public Post getPostById(String id) {
        Post post = postRepository.findById(String.valueOf(id)).get();
        return post;
    }

    public Post updatePost(String postId, Post updatedPost) {
        Post post = postRepository.findById(postId).get();
            post.setTitle(updatedPost.getTitle());
            post.setDescription(updatedPost.getDescription());
            post.setContent(updatedPost.getContent());
            return postRepository.save(post);
    }

    public PostDto getAllCommentsForParticularPost(String postId) {

        Post post = postRepository.findById(String.valueOf(postId)).get();
        ArrayList comments = restTemplate.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/v1/comments/" + postId, ArrayList.class);

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setComments(comments);
        return dto;
    }
}
