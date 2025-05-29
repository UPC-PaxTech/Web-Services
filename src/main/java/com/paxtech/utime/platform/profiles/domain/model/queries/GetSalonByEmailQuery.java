package com.paxtech.utime.platform.profiles.domain.model.queries;

public record GetSalonByEmailQuery(String email) {
    public GetSalonByEmailQuery {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or empty");
    }
}

