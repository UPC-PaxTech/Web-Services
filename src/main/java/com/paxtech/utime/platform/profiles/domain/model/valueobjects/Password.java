package com.paxtech.utime.platform.profiles.domain.model.valueobjects;

public record Password(String passwordhash) {
    public Password() {
        this(null);
    }
}
