package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salons;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.SalonResource;

public class SalonResourceFromEntityAssembler {
    public static SalonResource toResourceFromEntity(Salons entity) {
        return new SalonResource(entity.getId(), entity.getLocation(), entity.getPhone(), entity.getEmail(), entity.getPasswordHash());
    }
}
