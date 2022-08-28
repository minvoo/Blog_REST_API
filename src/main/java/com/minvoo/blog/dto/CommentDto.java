package com.minvoo.blog.dto;

import com.minvoo.blog.entity.Post;
import lombok.*;
import lombok.experimental.Accessors;

import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
