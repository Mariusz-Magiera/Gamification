package com.gamification.server.resources;

import com.gamification.server.model.User;
import com.gamification.server.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorizeResource {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/authorize")
    public Boolean authorize(@RequestBody User user) {
        return userRepository.findByNameAndPassword(user.getName(), DigestUtils.sha1Hex(user.getPassword())).isPresent();
    }
}
