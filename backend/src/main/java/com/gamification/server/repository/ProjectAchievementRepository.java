package com.gamification.server.repository;

import com.gamification.server.model.ProjectAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAchievementRepository extends JpaRepository<ProjectAchievement, Integer> {
}
