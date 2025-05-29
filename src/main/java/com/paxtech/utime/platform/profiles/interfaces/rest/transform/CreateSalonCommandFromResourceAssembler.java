package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateSalonResource;

public class CreateSalonCommandFromResourceAssembler {
    public static CreateSalonCommand toCommandFromResource(CreateSalonResource resource) {
        return new CreateSalonCommand(resource.imageUrl(), resource.location(), resource.phone(), resource.email(), resource.passwordHash());
    }
}
