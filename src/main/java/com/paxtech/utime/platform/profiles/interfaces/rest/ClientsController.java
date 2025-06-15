package com.paxtech.utime.platform.profiles.interfaces.rest;

import com.paxtech.utime.platform.iam.domain.model.aggregates.User;
import com.paxtech.utime.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.paxtech.utime.platform.profiles.domain.model.aggregates.Client;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllClientsQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetClientByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.ClientCommandService;
import com.paxtech.utime.platform.profiles.domain.services.ClientQueryService;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.ClientResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateClientResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.ClientResourceFrontEntityAssembler;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.CreateClientCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/clients", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Clients", description = "Endpoints for client management")
public class ClientsController {

    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientsQueryService;
    private final UserRepository userRepository;


    public ClientsController(ClientCommandService clientCommandService, ClientQueryService clientsQueryService, UserRepository userRepository) {
        this.clientCommandService = clientCommandService;
        this.clientsQueryService = clientsQueryService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Create a new client", description = "Registers a new client in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })

    @PostMapping
    public ResponseEntity<ClientResource> createClient(@RequestBody CreateClientResource resource) {
        var command = CreateClientCommandFromResourceAssembler.toCommandFromResource(resource);

        // Buscar el User por su ID (que viene en el comando)
        Optional<User> userOptional = userRepository.findById(command.userId());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Llamar al servicio para crear el Client con el User encontrado
        Optional<Client> client = clientCommandService.handle(command, userOptional.get());

        // Devolver el recurso creado o bad request si falla
        return client
                .map(value -> new ResponseEntity<>(ClientResourceFrontEntityAssembler.toResourceFromEntity(value), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @Operation(
            summary = "Get a client by ID",
            description = "Retrieve a client by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientResource> getClientByIdPath(@PathVariable Long id) {
        var result = clientsQueryService.handle(new GetClientByIdQuery(id));
        return result.map(client -> ResponseEntity.ok(ClientResourceFrontEntityAssembler.toResourceFromEntity(client)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Get all clients", description = "Retrieve all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping
    public ResponseEntity<?> getClients() {
            return getAllClients();
    }

    private ResponseEntity<List<ClientResource>> getAllClients() {
        List<Client> clients = clientsQueryService.handle(new GetAllClientsQuery());
        if (clients.isEmpty()) return ResponseEntity.notFound().build();
        var resources = clients.stream()
                .map(ClientResourceFrontEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
