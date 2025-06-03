package com.paxtech.utime.platform.profiles.interfaces.rest.controllers;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salon;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllSalonsQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.SalonCommandService;
import com.paxtech.utime.platform.profiles.domain.services.SalonsQueryService;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateSalonResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.SalonResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.CreateSalonCommandFromResourceAssembler;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.SalonResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/salons", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Salons", description = "Endpoints for salon management")
public class SalonsController {

    private final SalonCommandService salonCommandService;
    private final SalonsQueryService salonsQueryService;

    public SalonsController(SalonCommandService salonCommandService, SalonsQueryService salonsQueryService) {
        this.salonCommandService = salonCommandService;
        this.salonsQueryService = salonsQueryService;
    }

    @Operation(summary = "Create a new salon", description = "Registers a new salon in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Salon created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<SalonResource> createSalon(@RequestBody CreateSalonResource resource) {
        Optional<Salon> salon = salonCommandService.handle(CreateSalonCommandFromResourceAssembler.toCommandFromResource(resource));
        return salon.map(value -> new ResponseEntity<>(SalonResourceFromEntityAssembler.toResourceFromEntity(value), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get a salon by ID",
            description = "Retrieve a salon by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salon found"),
            @ApiResponse(responseCode = "404", description = "Salon not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SalonResource> getSalonByIdPath(@PathVariable Long id) {
        var result = salonsQueryService.handle(new GetSalonByIdQuery(id));
        return result.map(salon -> ResponseEntity.ok(SalonResourceFromEntityAssembler.toResourceFromEntity(salon)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<SalonResource> getSalonById(Long id) {
        Optional<Salon> salon = salonsQueryService.handle(new GetSalonByIdQuery(id));
        return salon.map(value -> ResponseEntity.ok(SalonResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get all salons",
            description = "Gets all salons in endpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite source(s) found"),
            @ApiResponse(responseCode = "404", description = "Favorite source(s) not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping
    public ResponseEntity<?> AllSalons() {
        return getAllSalons();
    }



    private ResponseEntity<List<SalonResource>> getAllSalons() {
        List<Salon> salons = salonsQueryService.handle(new GetAllSalonsQuery());
        if (salons.isEmpty()) return ResponseEntity.notFound().build();
        var resources = salons.stream()
                .map(SalonResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
