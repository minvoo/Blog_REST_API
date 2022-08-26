package com.minvoo.blog.service.impl;

import com.minvoo.blog.dto.CommentDto;
import com.minvoo.blog.dto.PostDto;
import com.minvoo.blog.entity.Comment;
import com.minvoo.blog.entity.Post;
import com.minvoo.blog.exception.BlogApiException;
import com.minvoo.blog.exception.ResourceNotFoundException;
import com.minvoo.blog.mapper.CommentMapper;
import com.minvoo.blog.mapper.PostMapper;
import com.minvoo.blog.repository.CommentRepository;
import com.minvoo.blog.repository.PostRepository;
import com.minvoo.blog.service.CommentService;
import com.minvoo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public CommentDto getCommentById(Long postId, Long id) {

        PostDto postDto = postService.getPostById(postId);
        Post post = PostMapper.mapToEntity(postDto);

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));

if (!comment.getPost().getId().equals(post.getId())) {
    throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belog to post");
        }

        return CommentMapper.mapToDTO(comment);

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

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long postId) {

       List<Comment> comments = commentRepository.findByPostId(postId);
       return comments.stream().map(comment -> CommentMapper.mapToDTO(comment)).collect(Collectors.toList());
    }
}
