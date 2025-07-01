package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Social;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSocialCommand;
import com.paxtech.utime.platform.profiles.domain.services.SocialCommandService;
import com.paxtech.utime.platform.profiles.domain.services.SocialQueryService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialCommandServiceImpl implements SocialCommandService {
    private final SocialRepository socialRepository;
    public SocialCommandServiceImpl(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }
    @Override
    public Optional<Social> handle(CreateSocialCommand command){
        var social = new Social(command);
        socialRepository.save(social);
        return Optional.of(social);
    }
}
