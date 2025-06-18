package com.paxtech.utime.platform.reservations.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Provider;
import com.paxtech.utime.platform.profiles.interfaces.acl.ProviderContextFacade;
import com.paxtech.utime.platform.reservations.domain.model.aggregates.Reservation;
import com.paxtech.utime.platform.reservations.interfaces.rest.resources.ProviderDto;
import com.paxtech.utime.platform.reservations.interfaces.rest.resources.ReservationDetailsResource;

public class ReservationDetailsResourceFromEntityAssembler {

    public static ReservationDetailsResource toResourceFromEntity(Reservation reservation, ProviderContextFacade providerContextFacade) {

        var provider = providerContextFacade.fetchProviderById(reservation.getProviderId())
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

        var providerDto = new ProviderDto(
                provider.getId(),
                provider.getUser().getEmail(),
                provider.getCompanyName()
        );
        return new ReservationDetailsResource(
                reservation.getId(),
                reservation.getClientId(),
                providerDto,
                reservation.getPaymentId(),
                reservation.getWorkerId()
        );
    }
}
