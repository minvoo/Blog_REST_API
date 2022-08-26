package com.minvoo.blog.service;

import com.minvoo.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getAllCommentsByPostId(Long postId);

    CommentDto getCommentById(Long postId, Long id);
}
