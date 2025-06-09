package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Client;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateClientCommand;
import com.paxtech.utime.platform.profiles.domain.services.ClientCommandService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {
    private final ClientRepository clientRepository;

    public ClientCommandServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> handle(CreateClientCommand command) {

        var client = new Client(command);
        var saved = clientRepository.save(client);
        return Optional.of(saved);
    }


}
