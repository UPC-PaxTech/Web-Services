package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.SalonProfileResource;

public class SalonProfileResourceFromEntityAssembler {
    public static SalonProfileResource toResourcefromEntity(SalonProfile entity) {
        return new SalonProfileResource(entity.getId(), entity.getCoverUrl().getUrl(), entity.getCoverUrl().getUrl() );
    }
}
