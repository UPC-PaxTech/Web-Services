package com.paxtech.utime.platform.profiles.application.internal.queryservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salon;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllSalonsQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonByEmailQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.SalonsQueryService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.SalonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of SalonsQueryService.
 */
@Service
public class SalonsQueryServiceImpl implements SalonsQueryService {

    private final SalonRepository salonRepository;

    public SalonsQueryServiceImpl(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    @Override
    public List<Salon> handle(GetAllSalonsQuery query) {
        return salonRepository.findAll();
    }

    @Override
    public Optional<Salon> handle(GetSalonByIdQuery query) {
        return salonRepository.findById(query.id());
    }

    @Override
    public Optional<Salon> handle(GetSalonByEmailQuery query) {
        return salonRepository.findByEmail(query.email());
    }
}
