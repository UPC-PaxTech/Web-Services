package com.paxtech.utime.platform.profiles.application.internal.queryservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Client;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllClientsQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetClientsByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.ClientsQueryService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of SalonsQueryService.
 */
@Service
public class ClientsQueryServiceImpl implements ClientsQueryService {

    private final ClientRepository clientRepository;

    public ClientsQueryServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> handle(GetAllClientsQuery query) {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> handle(GetClientsByIdQuery query) {
        return clientRepository.findById(query.id());
    }

}
