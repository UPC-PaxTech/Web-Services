package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonProfileByIdQuery;

import java.util.Optional;

public interface SalonProfileQueryService {
    Optional<SalonProfile> handle(GetSalonProfileByIdQuery query);
}
