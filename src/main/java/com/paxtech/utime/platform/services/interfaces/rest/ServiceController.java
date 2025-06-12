package com.paxtech.utime.platform.services.interfaces.rest;

import com.paxtech.utime.platform.services.domain.model.commands.CreateServiceCommand;
import com.paxtech.utime.platform.services.domain.model.queries.GetAllServicesQuery;
import com.paxtech.utime.platform.services.domain.services.ServiceCommandService;
import com.paxtech.utime.platform.services.domain.services.ServiceQueryService;
import com.paxtech.utime.platform.services.infrastructure.persistence.jpa.repositories.ServiceRepository;
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

@RestController
@RequestMapping(value = "/api/v1/services", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Services", description = "Available Service Endpoints")
public class ServiceController {
    private final ServiceCommandService serviceCommandService;
    private final ServiceQueryService serviceQueryService;

    public ServiceController(ServiceCommandService serviceCommandService, ServiceQueryService serviceQueryService) {
        this.serviceCommandService = serviceCommandService;
        this.serviceQueryService = serviceQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<ServiceResource> createService(@RequestBody CreateServiceResource resource) {
        var createServiceCommand = CreateServiceCommandFromResourceAssembler.toCommandFromResource(resource);
        var service = serviceCommandService.handle(createServiceCommand);
        if (service.isEmpty()) return ResponseEntity.badRequest().build();
        var createdService = service.get();
        var serviceResource = ServiceResourceFromEntityAssembler.toResourceFromEntity(createdService);
        return new ResponseEntity<>(serviceResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all Services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profiles found"),
            @ApiResponse(responseCode = "404", description = "Profiles not found")})
    public ResponseEntity<List<ServiceResource>> getALlServices(){
        var services = serviceQueryService.handle(new GetAllServicesQuery());
        if(services.isEmpty()) return ResponseEntity.notFound().build();
        var serviceResources = services.stream()
                .map(ServiceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(serviceResources);
    }

}
