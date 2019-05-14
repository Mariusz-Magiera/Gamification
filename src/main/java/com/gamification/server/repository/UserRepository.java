package com.gamification.server.repository;

import com.gamification.server.model.Project;
import com.gamification.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNameAndPassword(String name, String password);

    //@Query("SELECT p FROM project p INNER JOIN p.project_to_user WHERE pu.user=?1")
    //List<Project> findAllProjectsById(Integer id);
}
