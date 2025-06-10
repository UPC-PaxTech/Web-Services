package com.paxtech.utime.platform.profiles.interfaces.rest.resources;

import java.time.LocalDate;

public record ClientResource(
        Long id,
        String firstName,
        String lastName,
        String fullName,
        LocalDate birthDate,
        String phone,
        String email
) {}
