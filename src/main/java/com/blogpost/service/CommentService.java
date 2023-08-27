package com.blogpost.service;

import com.blogpost.payload.CommentDto;
import com.blogpost.repository.CommentRepository;
import com.blogpost.repository.PostRepository;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> findCommentByPostId(long postId);

    void deleteCommentById(long postId, long commentId);

    CommentDto getCommentByCommentId(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);
}
