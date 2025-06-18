package com.paxtech.utime.platform.reservations.interfaces.rest.resources;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Client;
import com.paxtech.utime.platform.profiles.domain.model.aggregates.Provider;
import com.paxtech.utime.platform.workers.domain.model.aggregates.Worker;

public record ReservationDetailsResource(
        Long id, Long clientId, ProviderDto provider, Long paymentId, Long workerId) {
}
