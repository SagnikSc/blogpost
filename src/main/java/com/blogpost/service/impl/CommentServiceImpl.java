package com.blogpost.service.impl;

import com.blogpost.entity.Comment;
import com.blogpost.entity.Post;
import com.blogpost.exception.ResourceNotFoundException;
import com.blogpost.payload.CommentDto;
import com.blogpost.repository.CommentRepository;
import com.blogpost.repository.PostRepository;
import com.blogpost.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId));
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return mapToDto(savedComment);
    }

    @Override
    public List<CommentDto> findCommentByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId));
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(commentId));

        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto getCommentByCommentId(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(commentId));

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {

        Post post = postRepository
                .findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId));

        Comment comment = commentRepository
                .findById(commentId).orElseThrow(() -> new ResourceNotFoundException(commentId));

        Comment comment1 = mapToEntity(commentDto);
        comment1.setId(comment.getId());
        comment1.setPost(post);

        Comment updatedComment = commentRepository.save(comment1);

        return mapToDto(updatedComment);
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }
}
