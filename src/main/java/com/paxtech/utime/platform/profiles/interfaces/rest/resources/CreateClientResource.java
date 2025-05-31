package com.paxtech.utime.platform.profiles.interfaces.rest.resources;

import java.util.Date;

public record CreateClientResource(String name, Date passwordHash, String birth_date) {
    public CreateClientResource {
        if (passwordHash == null)
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (birth_date == null)
            throw new IllegalArgumentException("Birth date cannot be null or empty");
    }
}
