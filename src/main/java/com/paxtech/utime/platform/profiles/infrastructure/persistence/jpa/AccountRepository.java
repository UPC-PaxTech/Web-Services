package com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa;
import com.paxtech.utime.platform.profiles.domain.model.aggregates.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);

    List<Account> findByIsActive(Boolean isActive);
}
