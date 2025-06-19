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

/**
 * SalonProfileController
 *
 * REST controller that handles provider profile creation and retrieval.
 */
@RestController
@RequestMapping(value = "/api/v1/provider-profiles", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Provider Profiles", description = "Endpoints for Provider Profiles")
public class SalonProfileController {

    private final SalonProfileCommandService salonProfileCommandService;
    private final SalonProfileQueryService salonProfileQueryService;

    /**
     * Constructor
     * @param salonProfileCommandService Service to handle commands related to provider profiles
     * @param salonProfileQueryService Service to handle queries related to provider profiles
     */
    public SalonProfileController(SalonProfileCommandService salonProfileCommandService, SalonProfileQueryService salonProfileQueryService) {
        this.salonProfileCommandService = salonProfileCommandService;
        this.salonProfileQueryService = salonProfileQueryService;
    }

    /**
     * Creates a new provider profile with a cover and profile image.
     * @param resource The {@link CreateSalonProfileResource} with the required data
     * @return A {@link SalonProfileResource} if created, or 400 Bad Request if creation fails
     */
    @Operation(
            summary = "Create a provider profile",
            description = "Create a provider profile with cover and profile image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Provider profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<SalonProfileResource> createSalonProfile(@RequestBody CreateSalonProfileResource resource) {
        Optional<SalonProfile> salonProfile = salonProfileCommandService.handle(
                CreateSalonProfileCommandFromResourceAssembler.toCommandFromResource(resource));

        return salonProfile
                .map(profile -> new ResponseEntity<>(
                        SalonProfileResourceFromEntityAssembler.toResourcefromEntity(profile), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Retrieves a provider profile by its ID.
     * @param id The ID of the provider profile
     * @return A {@link SalonProfileResource} if found, or 404 Not Found if it does not exist
     */
    @Operation(
            summary = "Get a provider profile by Id",
            description = "Gets a provider profile by the provided Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider profile found"),
            @ApiResponse(responseCode = "404", description = "Provider profile not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<SalonProfileResource> getSalonProfileById(@PathVariable Long id) {
        Optional<SalonProfile> salonProfile = salonProfileQueryService.handle(new GetSalonProfileByIdQuery(id));

        return salonProfile
                .map(profile -> ResponseEntity.ok(
                        SalonProfileResourceFromEntityAssembler.toResourcefromEntity(profile)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
