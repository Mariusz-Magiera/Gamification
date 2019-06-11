package com.gamification.server.repository;

import com.gamification.server.model.ProjectToUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectToUserRepository extends JpaRepository<ProjectToUser, Integer> {
}
