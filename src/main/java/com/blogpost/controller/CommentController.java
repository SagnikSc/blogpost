package com.blogpost.controller;

import com.blogpost.payload.CommentDto;
import com.blogpost.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> findCommentByPostId(@PathVariable("postId") long postId){
        List<CommentDto> dtos = commentService.findCommentByPostId(postId);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("postId") long postId,
                                                        @PathVariable("commentId") long commentId){
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("comment is deleted", HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable("postId") long postId,
                                                            @PathVariable("commentId") long commentId){
        return new ResponseEntity<>(commentService.getCommentByCommentId(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId,
                                                    @PathVariable("commentId") long commentId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }
}
