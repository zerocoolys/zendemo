package com.zendesk.demo.web;


import com.google.common.base.Strings;
import com.zendesk.demo.commons.WebResponse;
import com.zendesk.demo.model.User;
import com.zendesk.demo.repo.UserRepo;
import com.zendesk.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController implements BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public WebResponse login(
            @RequestParam String username,
            @RequestParam String password, HttpServletRequest request) {
        String token = userService.login(username, password);
        if (Strings.isNullOrEmpty(token)) {
            return err(-1, "username or password is wrong");
        } else {
            request.getSession().setAttribute("token", token);
        }
        return ok(token);
    }

}
