package com.minvoo.blog.mapper;

import com.minvoo.blog.dto.CommentDto;
import com.minvoo.blog.entity.Comment;

public class CommentMapper {


    public static Comment mapToEntity(CommentDto commentDto) {
        return new Comment().setId(commentDto.getId())
                .setName(commentDto.getName())
                .setEmail(commentDto.getEmail())
                .setBody(commentDto.getBody());
    }

    public static CommentDto mapToDTO(Comment comment) {
        return new CommentDto().setId(comment.getId())
                .setName(comment.getName())
                .setEmail(comment.getEmail())
                .setBody(comment.getBody());
    }
}
