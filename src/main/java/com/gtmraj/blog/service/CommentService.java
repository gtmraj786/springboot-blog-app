package com.gtmraj.blog.service;

import com.gtmraj.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId,CommentDto commentDto);

    void deleteById(Long postId, Long commentId);

}
