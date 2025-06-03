package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salon;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;
import com.paxtech.utime.platform.profiles.domain.services.SalonCommandService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.SalonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalonCommandServiceImpl implements SalonCommandService {
    private final SalonRepository salonRepository;

    public SalonCommandServiceImpl(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    @Override
    public Optional<Salon> handle(CreateSalonCommand command) {
        var salon = new Salon(command);
        var saved = salonRepository.save(salon);
        return Optional.of(saved);
    }
}
