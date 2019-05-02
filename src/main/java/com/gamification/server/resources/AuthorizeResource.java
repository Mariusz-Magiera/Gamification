package com.gamification.server.resources;

import com.gamification.server.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizeResource {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/authorize")
    public Boolean authorize(@RequestParam("name") String name, @RequestParam("password") String plaintext_password){
        return userRepository.findByNameAndPassword(name, DigestUtils.sha1Hex(plaintext_password)).isPresent();
    }
}
