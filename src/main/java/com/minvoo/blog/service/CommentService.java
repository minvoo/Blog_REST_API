package com.minvoo.blog.service;

import com.minvoo.blog.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

}
