package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.PortfolioImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioImageRepository extends JpaRepository<PortfolioImage, Long> {
}
