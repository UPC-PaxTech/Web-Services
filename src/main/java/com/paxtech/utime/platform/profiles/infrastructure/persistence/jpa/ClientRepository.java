package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa;


import com.paxtech.utime.platform.profiles.domain.model.aggregates.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {
}
