package com.gamification.server.resources;

import com.gamification.server.model.ProfileLink;
import com.gamification.server.model.User;
import com.gamification.server.repository.ProfileLinkRepository;
import com.gamification.server.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileLinkRepository profileLinkRepository;

    @GetMapping({"/all", "/", ""})
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<User> getUserById(@PathVariable final Integer id){
        return userRepository.findById(id);
    }

    @GetMapping("/id/{id}/links")
    public List<ProfileLink> getLinks(@PathVariable final Integer id){
        Optional<User> user = getUserById(id);
        if(user.isPresent()){
            return new ArrayList<>(user.get().getLinks());
        }
        return null;
    }

    
}
