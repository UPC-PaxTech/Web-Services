package com.paxtech.utime.platform.profiles.application.acl;

import com.paxtech.utime.platform.profiles.domain.model.queries.GetProviderByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.ProviderQueryService;
import com.paxtech.utime.platform.reservations.interfaces.rest.acl.ProviderContextFacade;
import com.paxtech.utime.platform.reservations.interfaces.rest.acl.ProviderDto;
/*
public class ProviderContextFacadeImpl implements ProviderContextFacade {
    private final ProviderQueryService providerQueryService;

    public ProvidersContextFacadeImpl(ProviderQueryService providerQueryService) {
        this.providerQueryService = providerQueryService;
    }

    @Override
    public ProviderDto fetchProviderById(Long providerId) {
        var query = new GetProviderByIdQuery(providerId);
        var salonOptional = providerQueryService.handle(query);

        return salonOptional.map(salon ->
                new ProviderDto(
                        salon.getId().getValue(),
                        salon.getCompanyName().getValue(),
                        salon.getImageUrl().getUrl(),
                        salon.getLocation().getLocation()
                )
        ).orElse(null);
    }
}
*/