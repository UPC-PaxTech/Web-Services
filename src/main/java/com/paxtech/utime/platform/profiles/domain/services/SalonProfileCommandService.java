package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.DeleteSalonProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.UpdateSalonProfileCommand;

import java.util.Optional;

public interface SalonProfileCommandService {
    Optional<SalonProfile> handle(CreateSalonProfileCommand command);

    Optional<SalonProfile> handle(UpdateSalonProfileCommand command);

    Optional<SalonProfile> handle(DeleteSalonProfileCommand command);
}
