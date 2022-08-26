package com.minvoo.blog.service.impl;

import com.minvoo.blog.dto.PostDto;
import com.minvoo.blog.entity.Post;
import com.minvoo.blog.entity.PostResponse;
import com.minvoo.blog.exception.ResourceNotFoundException;
import com.minvoo.blog.mapper.PostMapper;
import com.minvoo.blog.repository.PostRepository;
import com.minvoo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = PostMapper.mapToEntity(postDto);
        postRepository.save(post);
        return PostMapper.mapToDto(post);

    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = PostMapper.mapToDto(listOfPosts);

        return new PostResponse(content, posts.getNumber(),posts.getSize(), posts.getTotalElements(),
                posts.getTotalPages(), posts.isLast());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Post", "id", id)
                );
        return PostMapper.mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto dto, Long id) {
        Post post = PostMapper.mapToEntity(getPostById(id));

        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());
        post.setTitle(dto.getTitle());

        Post updatedPost = postRepository.save(post);
        return PostMapper.mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        postRepository.delete(post);
    }



}
