package com.gamification.server.resources;

import com.gamification.server.model.*;
import com.gamification.server.repository.PermissionRepository;
import com.gamification.server.repository.ProfileLinkRepository;
import com.gamification.server.repository.UserAchievementRepository;
import com.gamification.server.repository.UserRepository;
import javafx.util.Pair;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileLinkRepository profileLinkRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    UserAchievementRepository achievementRepository;


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

    @PostMapping("/add")
    public User addUser(@RequestBody User user){

        if(user.getPermission()==null){
            Optional<Permission> p = permissionRepository.findById(0);
            if(p.isPresent())
                user.setPermission(p.get());
        }
        user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("/id/{id}/projects")
    public List<Project> getProjects(@PathVariable final Integer id){
        Optional<User> user = getUserById(id);
        if(user.isPresent()){
            return new ArrayList<>(user.get().getProjects());
        }
        return null;
    }

    @PostMapping("/id/{id}/addLink")
    public Boolean addLink(@PathVariable final Integer id, @RequestBody final User user,
                                  @RequestParam(name="type") final String type, @RequestParam(name = "link") final String url){
        Optional<User> owner = getUserById(id),
                verified = userRepository.findByNameAndPassword(user.getName(), DigestUtils.sha1Hex(user.getPassword()));
        if(!(verified.isPresent() && owner.isPresent())){
            return false;
        }
        ProfileLink profileLink = new ProfileLink();
        profileLink.setType(type);
        profileLink.setUrl(url);
        if(owner.get().equals(verified.get())){
            profileLink.setUser(owner.get());
            profileLinkRepository.save(profileLink);
            return true;
        }
        return false;
    }

    @PostMapping("/id/{id}/addAchievement")
    public Boolean addAchievement(@PathVariable final Integer id, @RequestBody final User mod,
                                  @RequestParam(name="points") final Integer points, @RequestParam(name = "desc") final String description){
        UserAchievement achievement = new UserAchievement();
        achievement.setPoints(points);
        achievement.setDescription(description);
        Optional<User> user = getUserById(id),
                verifiedMod = userRepository.findByNameAndPassword(mod.getName(), DigestUtils.sha1Hex(mod.getPassword()));
        if(!verifiedMod.isPresent()){
            return false;
        }
        String perm = verifiedMod.get().getPermission().getName();
        if(user.isPresent() && (perm.equals("MOD") || perm.equals("ADMIN"))){
            achievement.setUser(user.get());
            achievementRepository.save(achievement);
            return true;
        }
        return false;
    }

    /*
    @PutMapping("/id/{id}/changePassword")
    public Boolean changePassword(@PathVariable final Integer id,
                                  @RequestParam("oldPassword") String oldPassword,
                                  @RequestParam("newPassword") String newPassword){
        Optional<User> user = getUserById(id);
        if(user.isPresent()){
            if( DigestUtils.sha1Hex(oldPassword)==user.get().getPassword()){
                user.get().setPassword(DigestUtils.sha1Hex(newPassword));
                userRepository.save(user.get());
                return true;
            }
        }
        return false;
    }
    */
}
