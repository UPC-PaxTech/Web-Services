package com.paxtech.utime.platform.profiles.interfaces.rest.resources;

public record CreateSalonResource(String imageUrl, String street, String city, String postalCode, String country, String phone, String email) {
    public CreateSalonResource {
        if (imageUrl == null || imageUrl.isBlank())
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        if (street == null || street.isBlank())
            throw new IllegalArgumentException("Location cannot be null or empty");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone cannot be null or empty");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or empty");
    }
}
