package com.paxtech.utime.platform.reservations.interfaces.rest.resources;

import com.paxtech.utime.platform.profiles.domain.model.valueobjects.CompanyName;

public record ProviderDto(Long id, String name, String companyName) {
}
