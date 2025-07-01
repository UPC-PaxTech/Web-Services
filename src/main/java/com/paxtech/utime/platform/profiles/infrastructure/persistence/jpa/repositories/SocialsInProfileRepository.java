package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.paxtech.utime.platform.profiles.domain.model.entities.SocialsInProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialsInProfileRepository extends JpaRepository<SocialsInProfile, Long> {
    List<SocialsInProfile> findByProviderProfileId(Long providerProfileId);
}
