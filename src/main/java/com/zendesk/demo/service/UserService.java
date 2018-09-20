package com.zendesk.demo.service;

import com.google.common.base.Strings;
import com.zendesk.demo.model.User;
import com.zendesk.demo.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserRepo userRepo;

    @Resource
    private SessionStore sessionStore;

    public String login(String username, String password) {
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            return null;
        }

        String md5 = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userRepo.findFirstByUsernameAndPassword(username, md5);

        if (user != null) {
            String token = UUID.randomUUID().toString();
            sessionStore.store(user.getId(), token);
            return token;
        } else {
            return null;
        }
    }

    public boolean existUser(String username) {
        return userRepo.findFirstByUsername(username) != null;
    }

    public User createUser(String username, String password) {
        String encoded = DigestUtils.md5DigestAsHex(password.getBytes()); // salt
        return userRepo.save(new User().setUsername(username).setPassword(encoded));
    }
}
