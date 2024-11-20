package com.microservice.comment.entity;

import lombok.*;
import javax.persistence.*;
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