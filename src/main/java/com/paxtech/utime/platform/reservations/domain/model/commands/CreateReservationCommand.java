package com.paxtech.utime.platform.reservations.domain.model.commands;

public record CreateReservationCommand(
        Long clientId,
        Long providerId,
        Long paymentId,
        String timeSlotId,
        Long workerId
) {}
