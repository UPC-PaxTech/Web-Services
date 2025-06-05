package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonProfileCommand;

import java.util.Optional;

public interface SalonProfileCommandService {
    Optional<SalonProfile> handle(CreateSalonProfileCommand command);
}
