package com.paxtech.utime.platform.profiles.domain.model.valueobjects;

import java.time.LocalDate;

public record ClientBirthDate(LocalDate birthDate) {

    public ClientBirthDate() {
        this(null);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
