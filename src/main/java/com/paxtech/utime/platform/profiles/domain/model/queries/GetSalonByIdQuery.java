package com.paxtech.utime.platform.profiles.domain.model.queries;

public record GetSalonByIdQuery(Long id) {
    public GetSalonByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Salon id cannot be null");
        }
    }
}
