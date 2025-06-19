package com.paxtech.utime.platform.services.interfaces.rest;

import com.paxtech.utime.platform.services.domain.model.commands.CreateServiceCommand;
import com.paxtech.utime.platform.services.domain.model.queries.GetAllServicesQuery;
import com.paxtech.utime.platform.services.domain.services.ServiceCommandService;
import com.paxtech.utime.platform.services.domain.services.ServiceQueryService;
import com.paxtech.utime.platform.services.interfaces.rest.resources.CreateServiceResource;
import com.paxtech.utime.platform.services.interfaces.rest.resources.ServiceResource;
import com.paxtech.utime.platform.services.interfaces.rest.transform.CreateServiceCommandFromResourceAssembler;
import com.paxtech.utime.platform.services.interfaces.rest.transform.ServiceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ServiceController
 *
 * REST controller for managing services offered by workers or providers.
 * Provides endpoints for creating and retrieving service records.
 */
@RestController
@RequestMapping(value = "/api/v1/services", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Services", description = "Available Service Endpoints")
public class ServiceController {

    private final ServiceCommandService serviceCommandService;
    private final ServiceQueryService serviceQueryService;

    /**
     * Constructor
     * @param serviceCommandService Service for handling service-related commands
     * @param serviceQueryService Service for querying service data
     */
    public ServiceController(ServiceCommandService serviceCommandService, ServiceQueryService serviceQueryService) {
        this.serviceCommandService = serviceCommandService;
        this.serviceQueryService = serviceQueryService;
    }

    /**
     * Creates a new service
     * @param resource The {@link CreateServiceResource} containing the data to create the service
     * @return A {@link ServiceResource} if created successfully, or 400 Bad Request if creation fails
     */
    @PostMapping
    @Operation(summary = "Create a new Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ServiceResource> createService(@RequestBody CreateServiceResource resource) {
        var createServiceCommand = CreateServiceCommandFromResourceAssembler.toCommandFromResource(resource);
        var service = serviceCommandService.handle(createServiceCommand);
        if (service.isEmpty()) return ResponseEntity.badRequest().build();
        var createdService = service.get();
        var serviceResource = ServiceResourceFromEntityAssembler.toResourceFromEntity(createdService);
        return new ResponseEntity<>(serviceResource, HttpStatus.CREATED);
    }

    /**
     * Retrieves all available services
     * @return A list of {@link ServiceResource} if found, or 404 Not Found if none exist
     */
    @GetMapping
    @Operation(summary = "Get all Services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found"),
            @ApiResponse(responseCode = "404", description = "Services not found")
    })
    public ResponseEntity<List<ServiceResource>> getAAllServices() {
        var services = serviceQueryService.handle(new GetAllServicesQuery());
        if (services.isEmpty()) return ResponseEntity.notFound().build();
        var serviceResources = services.stream()
                .map(ServiceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(serviceResources);
    }
}
