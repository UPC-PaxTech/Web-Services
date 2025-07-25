package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories;


import com.paxtech.utime.platform.profiles.domain.model.aggregates.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUserId(Long userId);
}
