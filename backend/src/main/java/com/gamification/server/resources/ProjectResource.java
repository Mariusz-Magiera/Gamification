package com.gamification.server.resources;

import com.gamification.server.model.*;
import com.gamification.server.repository.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectResource {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectAchievementRepository achievementRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectToUserRepository projectToUserRepository;

    @GetMapping({"/all", "/", ""})
    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Project> getProjectById(@PathVariable final Integer id){
        return projectRepository.findById(id);
    }

    @GetMapping("/id/{id}/tasks")
    public List<Task> getTasks(@PathVariable final Integer id){
        Optional<Project> project = getProjectById(id);
        if(project.isPresent()){
            return new ArrayList<>(project.get().getTasks());
        }
        return null;
    }

    @GetMapping("/id/{id}/users")
    public List<User> getUsers(@PathVariable final Integer id){
        Optional<Project> project = getProjectById(id);
        if(project.isPresent()){
            return new ArrayList<>(project.get().getUsers());
        }
        return null;
    }

    @PostMapping("add")
    public Boolean addProject(@RequestBody Project project){
        if(project.getName().isEmpty() || projectRepository.findByName(project.getName()).isPresent()){
            return false;
        }
        projectRepository.save(project);
        return true;
    }

    @PostMapping("/id/{id}/addUser")
    public Boolean addUser(@PathVariable final Integer id, @RequestBody final User user){
        Optional<User> verified = userRepository.findByNameAndPassword(user.getName(), DigestUtils.sha1Hex(user.getPassword()));
        Optional<Project> project = projectRepository.findById(id);
        if(!(verified.isPresent() && project.isPresent())) return false;

        if(project.get().getUsers().contains(verified.get())) return false;
        ProjectToUser pu = new ProjectToUser();
        pu.setProject(project.get().getId());
        pu.setUser(verified.get().getId());
        projectToUserRepository.save(pu);
        return true;
    }

    @PostMapping("/id/{id}/addTask")
    public Boolean addTask(@PathVariable final Integer id, @RequestBody final User mod,
                           @RequestParam(name="points") final Integer points, @RequestParam(name = "desc") final String description,
                           @RequestParam(name="due_date") final Date dueDate){
        Task task = new Task();
        task.setPoints(points);
        task.setDescription(description);
        task.setDue_date(dueDate);

        Optional<Project> project = getProjectById(id);
        Optional<User> verifiedMod = userRepository.findByNameAndPassword(mod.getName(), DigestUtils.sha1Hex(mod.getPassword()));
        if(!verifiedMod.isPresent()) return false;

        String perm = verifiedMod.get().getPermission().getName();
        if(project.isPresent() && (perm.equals("MOD") || perm.equals("ADMIN"))){
            task.setProject(project.get());
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    @PostMapping("/id/{id}/addAchievement")
    public Boolean addAchievement(@PathVariable final Integer id, @RequestBody final User mod,
                                  @RequestParam(name="points") final Integer points, @RequestParam(name = "desc") final String description){
        ProjectAchievement achievement = new ProjectAchievement();
        achievement.setPoints(points);
        achievement.setDescription(description);
        Optional<Project> project = getProjectById(id);
        Optional<User> verifiedMod = userRepository.findByNameAndPassword(mod.getName(), DigestUtils.sha1Hex(mod.getPassword()));
        if(!verifiedMod.isPresent()){
            return false;
        }
        String perm = verifiedMod.get().getPermission().getName();
        if(project.isPresent() && (perm.equals("MOD") || perm.equals("ADMIN"))){
            achievement.setProject(project.get());
            achievementRepository.save(achievement);
            return true;
        }
        return false;
    }


}
