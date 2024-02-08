package com.example.proxysellertesttask.service;

import com.example.proxysellertesttask.dto.CommentDto;
import com.example.proxysellertesttask.dto.PostDto;
import com.example.proxysellertesttask.entity.posts.Post;
import com.example.proxysellertesttask.entity.user.User;
import com.example.proxysellertesttask.exception.ResourceNotFoundException;
import com.example.proxysellertesttask.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SubscribeService subscribeService;

    public PostDto createPost(PostDto postDto, Long userId) {
        Post post = Post.builder()
                .id(sequenceGenerator.generateSequence(Post.SEQUENCE_NAME))
                .content(postDto.getContent())
                .createdAt(LocalDateTime.now())
                .userId(userId)
                .build();

        Post savedPost = postRepository.save(post);

        return PostMapper.mapToDto(savedPost, null, null);
    }

    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("The post with current id does not exists"));
        List<CommentDto> commentDtos = commentService.getCommentsByPostId(post.getId());
        return PostMapper.mapToDto(post, post.getLikes(), commentDtos);
    }

    public PostDto editPost(Long postId, PostDto postDto, Long userId) {
        Post post = postRepository.findByIdAndUserId(postId, userId).orElseThrow(() -> new ResourceNotFoundException("The post with current id does not exists"));
        post.setContent(postDto.getContent());
        post.setEditedAt(LocalDateTime.now());
        postRepository.save(post);
        return PostMapper.mapToDto(post, null, null);
    }

    public String deletePost(Long postId, Long userId) {
        Post post = postRepository.findByIdAndUserId(postId, userId).orElseThrow(() -> new ResourceNotFoundException("The post with current id does not exists"));
        postRepository.delete(post);
        return "Deleted successful ";
    }

    public List<PostDto> getSubscribedUserFeed(User user) {
        List<Long> subscribedUserIds = subscribeService.getSubscribedUserIds(user);
        subscribedUserIds.add(user.getId());
        List<Post> posts = postRepository.findByUserIdInOrderByCreatedAtDesc(subscribedUserIds);

        return posts.stream()
                .map(post -> {
                    List<CommentDto> commentDtos = commentService.getCommentsByPostId(post.getId());
                    return PostMapper.mapToDto(post, post.getLikes(), commentDtos);
                })
                .collect(Collectors.toList());
    }

    public List<PostDto> getAnotherUserFeed() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(post -> {
                    List<CommentDto> commentDtos = commentService.getCommentsByPostId(post.getId());
                    return PostMapper.mapToDto(post, post.getLikes(), commentDtos);
                })
                .collect(Collectors.toList());
    }
}
