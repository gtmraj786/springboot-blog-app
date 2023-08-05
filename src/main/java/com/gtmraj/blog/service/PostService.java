package com.gtmraj.blog.service;

import com.gtmraj.blog.payload.PostDto;
import com.gtmraj.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
//    List<PostDto> getAllPosts();
//     List<PostDto> getAllPosts(int pageNo, int pageSize);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long postId);

    PostDto updatePost(PostDto postDto,long id);

    void deletePostById(long id);

    List<PostDto> getPostsByCategory(Long categoryId);
}
