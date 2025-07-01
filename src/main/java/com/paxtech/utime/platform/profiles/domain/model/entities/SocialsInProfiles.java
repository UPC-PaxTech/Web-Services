package com.paxtech.utime.platform.profiles.domain.model.entities;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.ProviderProfile;
import com.paxtech.utime.platform.profiles.domain.model.aggregates.Social;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSocialsInProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.UpdateSocialsInProfileCommand;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class SocialsInProfiles extends AuditableAbstractAggregateRoot<SocialsInProfiles> {

    @Getter
    @Column(name = "social_id", nullable = false)
    private Long socialId;

    @Getter
    @Column(name = "salon_profile_id", nullable = false)
    private Long salonProfileId;

    protected SocialsInProfiles() {}

    public SocialsInProfiles(CreateSocialsInProfileCommand command) {
        validate(command.socialId(), command.providerProfileId());
        this.socialId = command.socialId();
        this.salonProfileId = command.providerProfileId();
    }

    public SocialsInProfiles update(UpdateSocialsInProfileCommand command) {
        validate(command.socialId(), command.salonProfileId());
        this.socialId = command.socialId();
        this.salonProfileId = command.salonProfileId();
        return this;
    }

    private void validate(Long socialId, Long salonProfileId) {
        if (socialId == null || socialId <= 0)
            throw new IllegalArgumentException("Invalid socialId");
        if (salonProfileId == null || salonProfileId <= 0)
            throw new IllegalArgumentException("Invalid salonProfileId");
    }}
