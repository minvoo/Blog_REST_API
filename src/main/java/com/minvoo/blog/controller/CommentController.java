package com.minvoo.blog.controller;

import com.minvoo.blog.dto.CommentDto;
import com.minvoo.blog.entity.Comment;
import com.minvoo.blog.entity.Post;
import com.minvoo.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") Long postId,
    @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
//        return ResponseEntity.ok(commentService.createComment(postId, commentDto));
    }

    @GetMapping("posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable("postId") Long postId) {
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") Long postId,
                                                     @PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.getCommentById(postId, id));
    }
}
