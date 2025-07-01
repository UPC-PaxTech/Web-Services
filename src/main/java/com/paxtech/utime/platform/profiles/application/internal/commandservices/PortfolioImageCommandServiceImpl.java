package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.PortfolioImage;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreatePortfolioImageCommand;
import com.paxtech.utime.platform.profiles.domain.services.PortfolioImageCommandService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.repositories.PortfolioImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PortfolioImageCommandServiceImpl implements PortfolioImageCommandService {
    private final PortfolioImageRepository portfolioImageRepository;

    public PortfolioImageCommandServiceImpl(PortfolioImageRepository portfolioImageRepository) {
        this.portfolioImageRepository = portfolioImageRepository;
    }

    @Override
    public Optional<PortfolioImage> handle(CreatePortfolioImageCommand command){
        var portfolioImage = portfolioImageRepository.save(new PortfolioImage(command));
        return Optional.of(portfolioImage);
    }


}
