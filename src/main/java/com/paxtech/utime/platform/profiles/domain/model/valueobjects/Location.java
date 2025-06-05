package com.paxtech.utime.platform.profiles.domain.model.valueobjects;

public record Location(String street, String city, String postalCode, String country) {
    public Location(){
        this(null, null, null, null);
    }



    public String getStreetAddress() {
        return "%s %s, %s, %s, %s".formatted(street, city, postalCode, country);
    }

    public Location {
        if (street == null || street.isBlank()){
            throw new IllegalArgumentException("street must not be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City must not be null or blank");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code must not be null or blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country must not be null or blank");
        }

    }
}
