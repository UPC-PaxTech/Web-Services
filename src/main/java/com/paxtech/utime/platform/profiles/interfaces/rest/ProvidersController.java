package com.paxtech.utime.platform.profiles.interfaces.rest;

import com.paxtech.utime.platform.iam.domain.model.aggregates.User;
import com.paxtech.utime.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.paxtech.utime.platform.profiles.domain.model.aggregates.Provider;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllProvidersQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetProviderByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.ProviderCommandService;
import com.paxtech.utime.platform.profiles.domain.services.ProviderQueryService;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateProviderResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.ProviderResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.CreateProviderCommandFromResourceAssembler;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.ProviderResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/providers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Providers", description = "Endpoints for provider management")
public class ProvidersController {

    private final ProviderCommandService providerCommandService;
    private final ProviderQueryService providerQueryService;
    private final UserRepository userRepository;

    public ProvidersController(ProviderCommandService providerCommandService, ProviderQueryService providerQueryService, UserRepository userRepository) {
        this.providerCommandService = providerCommandService;
        this.providerQueryService = providerQueryService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Create a new provider", description = "Registers a new provider in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Provider created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })




    @PostMapping
    public ResponseEntity<ProviderResource> createProvider(@RequestBody CreateProviderResource resource) {
        var command = CreateProviderCommandFromResourceAssembler.toCommandFromResource(resource);

        // Buscar el User por su ID
        Optional<User> userOptional = userRepository.findById(command.userId());

        if (userOptional.isEmpty()) return ResponseEntity.badRequest().build();

        Optional<Provider> provider = providerCommandService.handle(command, userOptional.get());

        return provider
                .map(value -> new ResponseEntity<>(ProviderResourceFromEntityAssembler.toResourceFromEntity(value), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get a provider by ID",
            description = "Retrieve a provider by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider found"),
            @ApiResponse(responseCode = "404", description = "Provider not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProviderResource> getProviderByIdPath(@PathVariable Long id) {
        var result = providerQueryService.handle(new GetProviderByIdQuery(id));
        return result.map(provider -> ResponseEntity.ok(ProviderResourceFromEntityAssembler.toResourceFromEntity(provider)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<ProviderResource> getProviderById(Long id) {
        Optional<Provider> provider = providerQueryService.handle(new GetProviderByIdQuery(id));
        return provider.map(value -> ResponseEntity.ok(ProviderResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get all providers",
            description = "Gets all providers in endpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite source(s) found"),
            @ApiResponse(responseCode = "404", description = "Favorite source(s) not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping
    public ResponseEntity<?> AllProviders() {
        return getAllProviders();
    }



    private ResponseEntity<List<ProviderResource>> getAllProviders() {
        List<Provider> providers = providerQueryService.handle(new GetAllProvidersQuery());
        if (providers.isEmpty()) return ResponseEntity.notFound().build();
        var resources = providers.stream()
                .map(ProviderResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
