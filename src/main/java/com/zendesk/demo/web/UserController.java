package com.zendesk.demo.web;

import com.google.common.base.Strings;
import com.zendesk.demo.commons.WebResponse;
import com.zendesk.demo.model.User;
import com.zendesk.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public WebResponse register(@RequestParam String username,
                                @RequestParam String password) {

        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            return err(-1, "username or password is illegle");
        }

        if (userService.existUser(username)) {
            return err(-2, "username exists.");
        }

        User user = userService.createUser(username, password);

        return ok(user);
    }
}
