package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateSalonResource;

public class CreateSalonCommandFromResourceAssembler {
    public static CreateSalonCommand toCommandFromResource(CreateSalonResource resource) {
        return new CreateSalonCommand(resource.imageUrl(), resource.street(), resource.city(), resource.postalCode(), resource.country(), resource.phone(), resource.email());
    }
}
