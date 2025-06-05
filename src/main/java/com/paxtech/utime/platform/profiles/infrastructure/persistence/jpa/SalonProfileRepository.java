package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonProfileRepository extends JpaRepository<SalonProfile, Long> {

}
