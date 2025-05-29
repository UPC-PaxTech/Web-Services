package com.paxtech.utime.platform.profiles.interfaces.rest.controllers;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Salons;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAllSalonsQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonByEmailQuery;
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
        Optional<Salons> salon = salonCommandService.handle(CreateSalonCommandFromResourceAssembler.toCommandFromResource(resource));
        return salon.map(value -> new ResponseEntity<>(SalonResourceFromEntityAssembler.toResourceFromEntity(value), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get salons with filters", description = "Retrieve salon(s) by ID, email, or get all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salon(s) retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Salon(s) not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @Parameters({
            @Parameter(name = "id", description = "Salon ID"),
            @Parameter(name = "email", description = "Salon email")
    })
    @GetMapping
    public ResponseEntity<?> getSalonsWithParameters(
            @Parameter(name = "params", hidden = true)
            @RequestParam Map<String, String> params) {

        if (params.containsKey("id")) {
            return getSalonById(Long.parseLong(params.get("id")));
        } else if (params.containsKey("email")) {
            return getSalonByEmail(params.get("email"));
        } else {
            return getAllSalons();
        }
    }

    private ResponseEntity<SalonResource> getSalonById(Long id) {
        Optional<Salons> salon = salonsQueryService.handle(new GetSalonByIdQuery(id));
        return salon.map(value -> ResponseEntity.ok(SalonResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<SalonResource> getSalonByEmail(String email) {
        Optional<Salons> salon = salonsQueryService.handle(new GetSalonByEmailQuery(email));
        return salon.map(value -> ResponseEntity.ok(SalonResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<List<SalonResource>> getAllSalons() {
        List<Salons> salons = salonsQueryService.handle(new GetAllSalonsQuery());
        if (salons.isEmpty()) return ResponseEntity.notFound().build();
        var resources = salons.stream()
                .map(SalonResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
