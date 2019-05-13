package com.gamification.server.resources;

import com.gamification.server.model.Project;
import com.gamification.server.model.Task;
import com.gamification.server.model.User;
import com.gamification.server.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectResource {

    @Autowired
    ProjectRepository projectRepository;

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
}
