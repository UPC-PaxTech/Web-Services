package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Clients;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateClientCommand;

import java.util.Optional;


public interface ClientCommandService {
    Optional<Clients> handle(CreateClientCommand command);
}
