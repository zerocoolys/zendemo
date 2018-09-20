package com.zendesk.demo.repo;

import com.zendesk.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostUserId(long userId);
    List<Comment> findAllByItemId(long itemId);



}
