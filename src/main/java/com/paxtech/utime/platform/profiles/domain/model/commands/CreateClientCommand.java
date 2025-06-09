package com.paxtech.utime.platform.profiles.domain.model.commands;

import java.time.LocalDate;

/**
 * @summary
 * Command to create a Client entity.
 */
public record CreateClientCommand(String firstName, String lastName, String email, String phone, LocalDate birthDate) {

    public CreateClientCommand {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("First name cannot be null or empty");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("Last name cannot be null or empty");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone cannot be null or empty");
        if (birthDate == null)
            throw new IllegalArgumentException("Birth date cannot be null");
    }
}
