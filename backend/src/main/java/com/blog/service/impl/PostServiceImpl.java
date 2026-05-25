package com.blog.service.impl;

import com.blog.entity.Post;
import com.blog.mapper.PostMapper;
import com.blog.service.PostService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<Post> list() {
        return postMapper.selectList(
            new LambdaQueryWrapper<Post>().orderByDesc(Post::getCreateTime)
        );
    }

    @Override
    public Post getById(Long id) {
        return postMapper.selectById(id);
    }

    @Override
    public Post create(Post post) {
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        if (post.getSummary() == null && post.getContent() != null) {
            post.setSummary(post.getContent().length() > 200
                ? post.getContent().substring(0, 200) : post.getContent());
        }
        postMapper.insert(post);
        return post;
    }

    @Override
    public void delete(Long id) {
        postMapper.deleteById(id);
    }
}
