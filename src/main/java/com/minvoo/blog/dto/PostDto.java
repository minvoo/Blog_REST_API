package com.minvoo.blog.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;


@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
