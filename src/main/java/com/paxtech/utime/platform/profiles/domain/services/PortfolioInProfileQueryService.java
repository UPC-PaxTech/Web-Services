package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.PortfolioInProfile;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllPortfolioInProfilesByProviderProfileIdQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetPortfolioInProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PortfolioInProfileQueryService {
    List<PortfolioInProfile> handle(GetAllPortfolioInProfilesByProviderProfileIdQuery query);
    Optional<PortfolioInProfile> handle(GetPortfolioInProfileByIdQuery query);

}
