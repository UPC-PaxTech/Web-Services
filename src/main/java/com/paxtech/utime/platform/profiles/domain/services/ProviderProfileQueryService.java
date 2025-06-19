package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.ProviderProfile;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonProfileByIdQuery;

import java.util.Optional;

public interface ProviderProfileQueryService {
    Optional<ProviderProfile> handle(GetSalonProfileByIdQuery query);
}
