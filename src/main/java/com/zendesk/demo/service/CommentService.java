package com.zendesk.demo.service;


import com.zendesk.demo.commons.WebResponse;
import com.zendesk.demo.model.Comment;
import com.zendesk.demo.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;


    public List<Comment> findCommentsByItemId(long itemId) {
        return commentRepo.findAllByItemId(itemId);
    }

    public List<Comment> findCommentsByUserId(long userId) {
        return commentRepo.findAllByPostUserId(userId);
    }

    public Comment createComment(long userId, long itemId, String content) {
        return commentRepo.save(new Comment().setPostUserId(userId).setContent(content).setItemId(itemId).setCreateTime(new Date()));
    }

    public Comment createComment(long commentId, String content) {
        Optional<Comment> commentOptional = commentRepo.findById(commentId);

        if (!commentOptional.isPresent()) {
            return null;
        }

        Comment pComment = commentOptional.get();
        return commentRepo.save(new Comment().setPostUserId(pComment.getPostUserId()).setItemId(pComment.getItemId()).setContent(content).setpId(commentId).setCreateTime(new Date()));
    }

    public List<Comment> findCommentsByCommentId(long commentId) {
        return commentRepo.findAll(Example.of(new Comment().setpId(commentId)));
    }
}
