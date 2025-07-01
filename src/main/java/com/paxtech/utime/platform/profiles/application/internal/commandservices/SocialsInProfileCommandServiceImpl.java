package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSocialsInProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.entities.SocialsInProfile;
import com.paxtech.utime.platform.profiles.domain.services.SocialsInProfileCommandService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories.SocialsInProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialsInProfileCommandServiceImpl implements SocialsInProfileCommandService {
    private final SocialsInProfileRepository socialsInProfileRepository;
    public SocialsInProfileCommandServiceImpl(SocialsInProfileRepository socialsInProfileRepository) {
        this.socialsInProfileRepository = socialsInProfileRepository;
    }

    @Override
    public Optional<SocialsInProfile> handle(CreateSocialsInProfileCommand command){
        var socialsInProfile = new SocialsInProfile(command);
        socialsInProfileRepository.save(socialsInProfile);
        return Optional.of(socialsInProfile);
    }
}
