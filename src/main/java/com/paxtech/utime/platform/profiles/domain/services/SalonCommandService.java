package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salons;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;

import java.util.Optional;

public interface SalonCommandService {
    Optional<Salons> handle(CreateSalonCommand command);
}
