package com.paxtech.utime.platform.profiles.application.internal.queryservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.ProviderProfile;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonProfileByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.ProviderProfileQueryService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories.ProviderProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderProfileQueryServiceImpl implements ProviderProfileQueryService {
    private final ProviderProfileRepository providerProfileRepository;
    public ProviderProfileQueryServiceImpl(ProviderProfileRepository providerProfileRepository) {
        this.providerProfileRepository = providerProfileRepository;
    }

    @Override
    public Optional<ProviderProfile> handle(GetSalonProfileByIdQuery query) {
        return providerProfileRepository.findById(query.id());
    }
}
