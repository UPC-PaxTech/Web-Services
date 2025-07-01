package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.entities.PortfolioInProfile;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllPortfolioInProfilesByProviderProfileIdQuery;

import java.util.List;

public interface PortfolioInProfileQueryService {
    List<PortfolioInProfile> handle(GetAllPortfolioInProfilesByProviderProfileIdQuery query);
}
