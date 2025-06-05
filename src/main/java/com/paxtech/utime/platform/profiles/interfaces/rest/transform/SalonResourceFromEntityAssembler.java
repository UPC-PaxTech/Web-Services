package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salon;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.SalonResource;

public class SalonResourceFromEntityAssembler {
    public static SalonResource toResourceFromEntity(Salon entity) {
        return new SalonResource(entity.getId(), entity.getStreet(), entity.getPhone(), entity.getEmail());
    }
}
