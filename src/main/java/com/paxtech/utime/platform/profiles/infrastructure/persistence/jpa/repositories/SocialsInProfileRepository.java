package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.paxtech.utime.platform.profiles.domain.model.entities.SocialsInProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialsInProfileRepository extends JpaRepository<SocialsInProfiles, Long> {
    List<SocialsInProfiles> findByProviderProfileId(Long providerProfileId);
}
