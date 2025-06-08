package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateAccountCommand;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateAccountResource;

public class CreateAccountCommandFromResourceAssembler {
    public static CreateAccountCommand toCommandFromResource(CreateAccountResource resource) {
        return new CreateAccountCommand(resource.username(), resource.password(), resource.isActive());
    }
}
