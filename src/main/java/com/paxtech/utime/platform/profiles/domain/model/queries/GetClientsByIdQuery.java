package com.paxtech.utime.platform.profiles.domain.model.queries;

public record GetClientsByIdQuery(Long id) {
    public GetClientsByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }
    }
}