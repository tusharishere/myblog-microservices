package com.microservice.post.controller;

import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/addpost")
    public ResponseEntity<Post> savePost(@RequestBody Post post){
        Post savedPost = postService.savePost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable String postId){
        Post post = postService.getPostById(postId);
        return post;
    }


    @PutMapping("/updatepost/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String postId, @RequestBody Post post){
        Post updatedPost = postService.updatePost(postId, post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<PostDto> getAllCommentsForParticularPost(@PathVariable String postId){
        PostDto postDto =  postService.getAllCommentsForParticularPost(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

}
