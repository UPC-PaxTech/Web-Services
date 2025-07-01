package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSocialsInProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.DeleteSocialsInProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.UpdateSocialsInProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.entities.SocialsInProfile;

import java.util.Optional;

public interface SocialsInProfileCommandService {
    Optional<SocialsInProfile> handle(CreateSocialsInProfileCommand command);

}
