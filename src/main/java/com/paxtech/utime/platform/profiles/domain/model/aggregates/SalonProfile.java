package com.paxtech.utime.platform.profiles.domain.model.aggregates;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.CoverImage;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.ProfileImage;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
public class SalonProfile extends AuditableAbstractAggregateRoot<SalonProfile> {

    @Getter
    @Embedded
    private ProfileImage profileUrl;

    @Getter
    @Embedded
    private CoverImage coverUrl;

    public SalonProfile updateInformation(String profileUrl, String coverUrl) {
        this.profileUrl = new ProfileImage(profileUrl);
        this.coverUrl = new CoverImage(coverUrl);
        return this;
    }

    public SalonProfile() {}

    public SalonProfile(CreateSalonProfileCommand command){
        this.profileUrl = new ProfileImage(command.profileUrl());
        this.coverUrl = new CoverImage(command.coverUrl());
    }



}
