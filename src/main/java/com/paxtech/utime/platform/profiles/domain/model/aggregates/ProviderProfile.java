package com.paxtech.utime.platform.profiles.domain.model.aggregates;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateProviderProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.CoverImage;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.ProfileImage;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class ProviderProfile extends AuditableAbstractAggregateRoot<ProviderProfile> {

    @Getter
    @Embedded
    private ProfileImage profileUrl;

    @Getter
    @Embedded
    private CoverImage coverUrl;

    @Getter
    @Column(nullable = false)
    private Long ProviderId;



    public ProviderProfile updateInformation(String profileUrl, String coverUrl) {
        this.profileUrl = new ProfileImage(profileUrl);
        this.coverUrl = new CoverImage(coverUrl);
        return this;
    }

    public ProviderProfile() {}

    public ProviderProfile(CreateProviderProfileCommand command){
        this.profileUrl = new ProfileImage(command.profileUrl());
        this.coverUrl = new CoverImage(command.coverUrl());
    }



}
