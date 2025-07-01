package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreatePortfolioInProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.entities.PortfolioInProfile;
import com.paxtech.utime.platform.profiles.domain.services.PortfolioImageCommandService;
import com.paxtech.utime.platform.profiles.domain.services.PortfolioInProfileCommandService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories.PortfolioImageRepository;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories.PortfolioInProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PortfolioInProfileCommandServiceImpl implements PortfolioInProfileCommandService {
    private final PortfolioInProfileRepository portfolioInProfileRepository;

    public PortfolioInProfileCommandServiceImpl(PortfolioInProfileRepository portfolioInProfileRepository) {
        this.portfolioInProfileRepository = portfolioInProfileRepository;
    }

    @Override
    public Optional<PortfolioInProfile> handle(CreatePortfolioInProfileCommand command){
        var portfolioInProfile = new PortfolioInProfile(command);
        portfolioInProfileRepository.save(portfolioInProfile);
        return Optional.of(portfolioInProfile);
    }
}
