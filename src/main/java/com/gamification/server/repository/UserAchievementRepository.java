package com.gamification.server.repository;

import com.gamification.server.model.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAchievementRepository extends JpaRepository<UserAchievement, Integer> {
}
