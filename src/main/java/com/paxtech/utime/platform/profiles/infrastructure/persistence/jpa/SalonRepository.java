package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface SalonRepository extends JpaRepository<Salons, Long> {
    boolean existsByEmail(String email);
    Optional<Salons> findByEmail(String email);

}
