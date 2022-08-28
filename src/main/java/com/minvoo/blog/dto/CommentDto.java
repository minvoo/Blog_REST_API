package com.minvoo.blog.dto;

import com.minvoo.blog.entity.Post;
import lombok.*;
import lombok.experimental.Accessors;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Body should not be empty")
    @Size(min=10, message = "Body should have at least 10 characters")
    private String body;
}
