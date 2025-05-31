package com.paxtech.utime.platform.profiles.domain.model.commands;

import java.util.Date;

/**
 * @summary
 * Command to create a Client entity.
 */

public record CreateClientCommand(String name, String passwordHash, Date birthDate) {

    public CreateClientCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (passwordHash == null || passwordHash.isBlank())
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        if (birthDate == null)
            throw new IllegalArgumentException("Birth date cannot be null");
    }
}