package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.paxtech.utime.platform.profiles.domain.model.entities.PortfolioInProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioInProfileRepository extends JpaRepository<PortfolioInProfile, Long> {
    List<PortfolioInProfile> findByProviderProfileId(Long providerProfileId);
}
