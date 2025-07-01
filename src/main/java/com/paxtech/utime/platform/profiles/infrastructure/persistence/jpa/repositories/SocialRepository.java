package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository<Social, Long> {
}
