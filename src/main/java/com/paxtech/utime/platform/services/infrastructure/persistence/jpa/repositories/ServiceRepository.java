package com.paxtech.utime.platform.services.infrastructure.persistence.jpa.repositories;

import com.paxtech.utime.platform.services.domain.model.aggregates.Service;
import com.paxtech.utime.platform.services.domain.model.valueobjects.Name;
import com.paxtech.utime.platform.services.domain.model.valueobjects.SalonId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findBySalonId(SalonId salonId);

    boolean existsBySalonIdAndName(SalonId salonId, Name name);

    List<Service> findByNameAndSalonId(Name name, SalonId salonId);
}
