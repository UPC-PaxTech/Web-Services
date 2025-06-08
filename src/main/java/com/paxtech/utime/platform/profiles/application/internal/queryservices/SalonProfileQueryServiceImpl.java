package com.paxtech.utime.platform.profiles.application.internal.queryservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonProfileByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.SalonProfileQueryService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.SalonProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalonProfileQueryServiceImpl implements SalonProfileQueryService {
    private final SalonProfileRepository salonProfileRepository;
    public SalonProfileQueryServiceImpl(SalonProfileRepository salonProfileRepository) {
        this.salonProfileRepository = salonProfileRepository;
    }

    @Override
    public Optional<SalonProfile> handle(GetSalonProfileByIdQuery query) {
        return salonProfileRepository.findById(query.id());
    }
}
