package com.paxtech.utime.platform.profiles.domain.model.queries;

public record GetSalonProfileByIdQuery(Long id) {
    public GetSalonProfileByIdQuery {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
    }
}
