package com.minvoo.blog.service.impl;

import com.minvoo.blog.dto.CommentDto;
import com.minvoo.blog.dto.PostDto;
import com.minvoo.blog.entity.Comment;
import com.minvoo.blog.entity.Post;
import com.minvoo.blog.exception.ResourceNotFoundException;
import com.minvoo.blog.mapper.CommentMapper;
import com.minvoo.blog.mapper.PostMapper;
import com.minvoo.blog.repository.CommentRepository;
import com.minvoo.blog.repository.PostRepository;
import com.minvoo.blog.service.CommentService;
import com.minvoo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostService postService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

//        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        PostDto postDto = postService.getPostById(postId);
        Post post = PostMapper.mapToEntity(postDto);

        Comment comment = CommentMapper.mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.mapToDTO(savedComment);
    }


}
