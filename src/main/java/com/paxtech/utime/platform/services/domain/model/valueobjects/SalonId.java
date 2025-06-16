package com.paxtech.utime.platform.services.domain.model.valueobjects;

public record SalonId(Long providerId) {
    public Long getSalonId() {
        return providerId;
    }
}
