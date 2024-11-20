package com.microservice.comment.service;

import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private RestTemplate restTemplate;

    public CommentService(CommentRepository commentRepository, RestTemplate restTemplate) {
        this.commentRepository = commentRepository;
        this.restTemplate = restTemplate;
    }

    public Comment saveComment(Comment comment) {
        Post post = restTemplate.getForObject("http://POST-SERVICE/api/v1/post/" + comment.getPostId(), Post.class);
        if (post != null) {
            String radomCommentId = UUID.randomUUID().toString();
            comment.setCommentId(radomCommentId);
            comment.setPostId(comment.getPostId());
            return commentRepository.save(comment);
        }
        return null;
    }

    public List<Comment> getCommentByPostId(String postId){
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }
}
