package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.DeleteSalonProfileCommand;
import com.paxtech.utime.platform.profiles.domain.services.SalonProfileCommandService;
import com.paxtech.utime.platform.profiles.domain.services.SalonProfileQueryService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.SalonProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SalonProfileCommandServiceImpl implements SalonProfileCommandService {
    private final SalonProfileRepository salonProfileRepository;
    public SalonProfileCommandServiceImpl(SalonProfileRepository salonProfileRepository) {
        this.salonProfileRepository = salonProfileRepository;
    }
    @Override
    public Optional<SalonProfile> handle(CreateSalonProfileCommand command) {
        var salonProfile = salonProfileRepository.save(new SalonProfile(command));
        var createdSalonProfile = salonProfileRepository.save(salonProfile);
        return Optional.of(createdSalonProfile);
    }

    /*Falta Update y delete*/
}
