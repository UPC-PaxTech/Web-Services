package com.paxtech.utime.platform.profiles.interfaces.rest;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetSalonProfileByIdQuery;
import com.paxtech.utime.platform.profiles.domain.services.SalonProfileCommandService;
import com.paxtech.utime.platform.profiles.domain.services.SalonProfileQueryService;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateSalonProfileResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.SalonProfileResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.CreateSalonProfileCommandFromResourceAssembler;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.SalonProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/provider-profiles", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Provider Profiles", description = "Endpoints for Provider Profiles")
public class SalonProfileController {
    private final SalonProfileCommandService salonProfileCommandService;
    private final SalonProfileQueryService salonProfileQueryService;

    public SalonProfileController(SalonProfileCommandService salonProfileCommandService, SalonProfileQueryService salonProfileQueryService) {
        this.salonProfileCommandService = salonProfileCommandService;
        this.salonProfileQueryService = salonProfileQueryService;
    }

    @Operation(
            summary = "Create a provider profile",
            description = "Create a provider profile with cover and profile image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Provider profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<SalonProfileResource> createSalonProfile(@RequestBody CreateSalonProfileResource resource) {
        Optional<SalonProfile> salonProfile = salonProfileCommandService.handle(CreateSalonProfileCommandFromResourceAssembler.toCommandFromResource(resource));
        return salonProfile.map(s-> new ResponseEntity<>(SalonProfileResourceFromEntityAssembler.toResourcefromEntity(s), CREATED)).orElseGet(()->ResponseEntity.badRequest().build());
    }


    @Operation(summary = "Get a provider profile by Id",
            description = "Gets a provider profile by the provided Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider profile found"),
            @ApiResponse(responseCode = "404", description = "Provider Profile not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<SalonProfileResource> getSalonProfileById(@PathVariable Long id) {
        Optional<SalonProfile> salonProfile = salonProfileQueryService.handle(new GetSalonProfileByIdQuery(id));
        return salonProfile.map(s->ResponseEntity.ok(SalonProfileResourceFromEntityAssembler.toResourcefromEntity(s))).orElseGet(()->ResponseEntity.notFound().build());
    }
}
