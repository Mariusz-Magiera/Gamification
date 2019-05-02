package com.gamification.server.resources;

import com.gamification.server.repository.UserRepository;
import javafx.util.Pair;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthorizeResource {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/authorize")
    public Boolean authorize(@RequestBody Pair<String, String> userData) {
        return userRepository.findByNameAndPassword(userData.getKey(), DigestUtils.sha1Hex(userData.getValue())).isPresent();
    }
}
