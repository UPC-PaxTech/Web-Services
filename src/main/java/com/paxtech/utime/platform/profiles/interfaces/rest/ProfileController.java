package com.paxtech.utime.platform.profiles.interfaces.rest;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Provider;
import com.paxtech.utime.platform.profiles.domain.model.aggregates.ProviderProfile;
import com.paxtech.utime.platform.profiles.domain.model.entities.PortfolioInProfile;
import com.paxtech.utime.platform.profiles.domain.model.entities.SocialsInProfile;
import com.paxtech.utime.platform.profiles.domain.services.ProviderQueryService;
import com.paxtech.utime.platform.profiles.domain.services.ProviderProfileQueryService;
import com.paxtech.utime.platform.profiles.domain.services.ProfileQueryService;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.paxtech.utime.platform.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provider-profiles")
@Tag(name = "Provider Profiles", description = "Endpoints for viewing provider profile info")
public class ProfileController {

    private final ProviderProfileQueryService providerProfileQueryService;
    private final ProviderQueryService providerQueryService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(
            ProviderProfileQueryService providerProfileQueryService,
            ProviderQueryService providerQueryService,
            ProfileQueryService profileQueryService) {
        this.providerProfileQueryService = providerProfileQueryService;
        this.providerQueryService = providerQueryService;
        this.profileQueryService = profileQueryService;
    }

    @Operation(summary = "Get a provider profile by id", responses = {
            @ApiResponse(responseCode = "200", description = "Profile found"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        var profile = providerProfileQueryService.handle(id);
        if (profile.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResource("Profile not found"));
        }

        var provider = providerQueryService.handleByProfileId(id);
        if (provider.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResource("Provider not found"));
        }

        List<SocialsInProfile> socials = profileQueryService.handleByProfileIdForSocials(id);
        List<PortfolioInProfile> portfolios = profileQueryService.handleByProfileIdForPortfolio(id);

        var resource = ProfileResourceFromEntityAssembler.toResourceFromEntity(
                profile.get(),
                provider.get(),
                socials,
                portfolios
        );

        return ResponseEntity.ok(resource);
    }
}
