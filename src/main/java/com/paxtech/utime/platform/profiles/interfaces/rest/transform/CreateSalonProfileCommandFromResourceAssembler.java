package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonProfileCommand;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateSalonProfileResource;

public class CreateSalonProfileCommandFromResourceAssembler {
    public static CreateSalonProfileCommand toCommandFromResource(CreateSalonProfileResource resource) {
        return new CreateSalonProfileCommand(resource.profileUrl(), resource.coverUrl());
    }
}
