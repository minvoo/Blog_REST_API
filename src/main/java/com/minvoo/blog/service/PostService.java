package com.minvoo.blog.service;

import com.minvoo.blog.dto.PostDto;
import com.minvoo.blog.entity.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto dto, Long id);

    void deletePostById(Long id);
}
