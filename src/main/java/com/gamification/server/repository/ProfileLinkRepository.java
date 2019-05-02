package com.gamification.server.repository;

import com.gamification.server.model.ProfileLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileLinkRepository extends JpaRepository<ProfileLink, Integer> {
}
