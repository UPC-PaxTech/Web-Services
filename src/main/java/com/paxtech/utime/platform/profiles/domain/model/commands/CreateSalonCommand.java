package com.paxtech.utime.platform.profiles.domain.model.commands;

/**
 * @summary
 * Command to create a Salon entity.
 */
public record CreateSalonCommand(String imageUrl, String location, String phone, String email, String passwordHash) {

    public CreateSalonCommand {
        if (imageUrl == null || imageUrl.isBlank())
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        if (location == null || location.isBlank())
            throw new IllegalArgumentException("Location cannot be null or empty");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone cannot be null or empty");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (passwordHash == null || passwordHash.isBlank())
            throw new IllegalArgumentException("Password hash cannot be null or empty");
    }
}
