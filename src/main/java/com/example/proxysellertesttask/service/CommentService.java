package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.dto.CommentDto;
import com.example.proxysellertesttask.entity.posts.Comment;
import com.example.proxysellertesttask.repository.CommentRepository;
import com.example.proxysellertesttask.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;


    public CommentDto createComment(String content, Long userId, Long postId) {
        postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with id " + postId + " not found"));

        Comment comment = Comment.builder()
                .id(sequenceGenerator.generateSequence(Comment.SEQUENCE_NAME))
                .content(content)
                .userId(userId)
                .postId(postId)
                .createdAt(LocalDateTime.now())
                .build();
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.mapToDto(savedComment);
    }

    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(CommentMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
