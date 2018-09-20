package com.zendesk.demo.repo;

import com.zendesk.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);

    User findFirstByUsernameAndPassword(String username, String password);
}
