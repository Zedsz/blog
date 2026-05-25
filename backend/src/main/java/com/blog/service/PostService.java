package com.blog.service;

import com.blog.entity.Post;
import java.util.List;

public interface PostService {
    List<Post> list();
    Post getById(Long id);
    Post create(Post post);
    void delete(Long id);
}
