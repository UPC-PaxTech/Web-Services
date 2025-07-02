package com.paxtech.utime.platform.profiles.interfaces.rest;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Social;
import com.paxtech.utime.platform.profiles.domain.model.aggregates.SocialInProfile;
import com.paxtech.utime.platform.profiles.domain.model.commands.*;
import com.paxtech.utime.platform.profiles.domain.model.queries.*;
import com.paxtech.utime.platform.profiles.domain.services.*;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.*;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.CreatePortfolioImageCommandFromResourceAssembler;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.CreateProviderProfileCommandFromResourceAssembler;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.CreateSocialCommandFromResourceAssembler;
import com.paxtech.utime.platform.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/profilesCHECKTEST")
@Tag(name = "Provider Profiles", description = "Endpoints for managing provider profiles and their relations")
public class ProviderProfileController {

    private final ProviderProfileQueryService providerProfileQueryService;
    private final ProviderQueryService providerQueryService;
    private final SocialInProfileQueryService socialsInProfileQueryService;
    private final SocialQueryService socialQueryService;
    private final PortfolioInProfileQueryService portfolioInProfileQueryService;
    private final ProviderProfileCommandService providerProfileCommandService;
    private final SocialCommandService socialCommandService;
    private final PortfolioImageCommandService portfolioImageCommandService;
    private final SocialInProfileCommandService socialInProfileCommandService;
    private final PortfolioInProfileCommandService portfolioInProfileCommandService;


    public ProviderProfileController(
            ProviderProfileQueryService providerProfileQueryService,
            ProviderQueryService providerQueryService,
            SocialInProfileQueryService socialsInProfileQueryService,
            SocialQueryService socialQueryService,
            PortfolioInProfileQueryService portfolioInProfileQueryService,
            SocialCommandService socialCommandService,
            ProviderProfileCommandService providerProfileCommandService,
            PortfolioImageCommandService portfolioCommandService, SocialInProfileCommandService socialInProfileCommandService, PortfolioInProfileCommandService portfolioInProfileCommandService) {
        this.providerProfileQueryService = providerProfileQueryService;
        this.providerQueryService = providerQueryService;
        this.socialsInProfileQueryService = socialsInProfileQueryService;
        this.socialQueryService = socialQueryService;
        this.portfolioInProfileQueryService = portfolioInProfileQueryService;
        this.providerProfileCommandService = providerProfileCommandService;
        this.socialCommandService = socialCommandService;
        this.portfolioImageCommandService = portfolioCommandService;
        this.socialInProfileCommandService = socialInProfileCommandService;
        this.portfolioInProfileCommandService = portfolioInProfileCommandService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFullProfile(@PathVariable Long id) {
        var profileOpt = providerProfileQueryService.handle(new GetProviderProfileByIdQuery(id));
        if (profileOpt.isEmpty())
            return ResponseEntity.status(404).body(new MessageResource("Profile not found"));

        var profile = profileOpt.get();

        var providerQuery = new GetProviderByIdQuery(profile.getProviderId());
        var providerOpt = providerQueryService.handle(providerQuery);
        if (providerOpt.isEmpty())
            return ResponseEntity.status(404).body(new MessageResource("Provider not found"));

        var provider = providerOpt.get();


        // Relaciones
        var socialLinks = socialsInProfileQueryService.handle(new GetAllSocialsInProfileByProviderProfileIdQuery(profile.getId()));
        Map<String, String> socialsMap = socialLinks.stream()
                .map(link -> socialQueryService.handle(new GetSocialByIdQuery(link.getSocial().getId())))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(
                        Social::getSocialIcon,  // clave del mapa (icono)
                        Social::getSocialUrl,   // valor del mapa (URL)
                        (existing, replacement) -> existing // manejo de duplicados
                ));



        var portfolioLinks = portfolioInProfileQueryService.handle(new GetAllPortfolioInProfilesByProviderProfileIdQuery(profile.getId()));
        List<PortfolioImageResource> portfolioImages = portfolioLinks.stream()
                .map(link -> new PortfolioImageResource(link.getPortfolio().getImageUrl()))
                .toList();

        var resource = new ProfileResource(
                provider.getId(),
                provider.getCompanyName(),
                "String Location",
                provider.getUser().getEmail(),
                profile.getProfileImageUrl(),
                profile.getCoverImageUrl(),
                socialsMap,
                portfolioImages
        );


        return ResponseEntity.ok(resource);
    }


    @PostMapping
    @Operation(summary = "Create a new profile", description = "Create a new profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profile created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Profile not found")})
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateFullProfileResource resource) {
        var providerProfileResource = new CreateProviderProfileResource(resource.profileImageUrl(), resource.coverImageUrl(), resource.providerId());
        var createProviderProfileCommand = CreateProviderProfileCommandFromResourceAssembler.toCommandFromResource(providerProfileResource);
        var providerProfile = providerProfileCommandService.handle(createProviderProfileCommand);

        if (resource.socials() != null && !resource.socials().isEmpty()) {
            resource.socials().forEach((socialIcon, socialUrl) -> {
                var socialResource = new CreateSocialResource(socialUrl, socialIcon);
                var createSocialCommand = CreateSocialCommandFromResourceAssembler.toCommandFromResource(socialResource);
                // Asumo que tienes un socialCommandService similar al providerProfileCommandService
                var social = socialCommandService.handle(createSocialCommand);
                if (social.isEmpty()) return;
                var socialInProfile = socialInProfileCommandService.handle(new CreateSocialInProfileCommand(providerProfile.get().getId(),social.get().getId()));

            });

        }
        if (resource.portfolioImages() != null && !resource.portfolioImages().isEmpty()) {
            resource.portfolioImages().forEach(imageUrl -> {
                var portfolioImageResource = new CreatePortfolioImageResource(imageUrl);
                var createPortfolioImageCommand = CreatePortfolioImageCommandFromResourceAssembler.toCommandFromResource(
                        portfolioImageResource
                );
                var portfolioImage = portfolioImageCommandService.handle(createPortfolioImageCommand);
                if (portfolioImage.isEmpty()) return;
                var portfolioInProfile = portfolioInProfileCommandService.handle(new CreatePortfolioInProfileCommand(providerProfile.get().getId(),portfolioImage.get().getId()));
            });
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ProfileResource(
                        providerProfile.get().getId(),
                        resource.companyName(),
                        resource.location(),
                        resource.email(),
                        providerProfile.get().getProfileImageUrl(),
                        providerProfile.get().getCoverImageUrl(),
                        resource.socials(),
                        resource.portfolioImages().stream().map(PortfolioImageResource::new).toList()
                )
        );

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a provider profile", description = "Delete a provider profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profile deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        try {
            providerProfileCommandService.handle(new DeleteProviderProfileCommand(id));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResource(e.getMessage()));
        }
    }

}