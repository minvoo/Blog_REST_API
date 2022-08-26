package com.minvoo.blog.mapper;

import com.minvoo.blog.dto.PostDto;
import com.minvoo.blog.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {


    public static Post mapToEntity(PostDto dto) {
        return new Post().setId(dto.getId())
                .setTitle(dto.getTitle())
                .setContent(dto.getContent())
                .setDescription(dto.getDescription());
    }

    public static PostDto mapToDto(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getDescription(), post.getContent());
    }

    public static List<PostDto> mapToDto(List<Post> posts) {
        return posts.stream()
                .map(PostMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
