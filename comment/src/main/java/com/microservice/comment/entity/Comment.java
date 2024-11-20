package com.microservice.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    private String commentId;
    private String body;
    private String email;
    private String name;
    private String postId;

}