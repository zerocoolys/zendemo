package com.zendesk.demo.web;

import com.zendesk.demo.commons.WebResponse;
import com.zendesk.demo.conf.SessionUtils;
import com.zendesk.demo.model.Comment;
import com.zendesk.demo.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class CommentController implements BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);


    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/item/{itemId}")
    public WebResponse commentsByItem(@PathVariable long itemId) {
        return ok(commentService.findCommentsByItemId(itemId));
    }

    @GetMapping("/comments/user")
    public WebResponse commentsByUser(HttpServletRequest request) {
        return ok(commentService.findCommentsByUserId(SessionUtils.getUserId(request)));
    }

    @PostMapping("/comments/item/{itemId}")
    public WebResponse createCommentByItemId(@PathVariable long itemId, @RequestBody String content, HttpServletRequest request) {

        long userId = SessionUtils.getUserId(request);
        if (userId == -1) {
            return err(-1);
        } else {
            Comment comment = commentService.createComment(userId, itemId, content);
            return ok(comment);
        }

    }

    @PostMapping("/comments/{commentId}")
    public WebResponse comments(@PathVariable long commentId, String content) {
        Comment comment = commentService.createComment(commentId, content);

        if (comment == null) {
            return err(-1, "comment not exists");
        } else {
            return ok(comment);
        }
    }

    @GetMapping("/comments/{commentId}")
    public WebResponse commentsByParentComment(@PathVariable long commentId) {
        return ok(commentService.findCommentsByCommentId(commentId));
    }
}
