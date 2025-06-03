package com.paxtech.utime.platform.profiles.domain.model.aggregates;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.Image;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.Location;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Salon extends AuditableAbstractAggregateRoot<Salon> {

    @Embedded
    private Image imageUrl;

    @Embedded
    private Location location;

    @Embedded
    private Contact salonContact;



    @Column(length = 9, nullable = false)
    @Getter
    private String phone;

    @Column(length = 25, nullable = false)
    @Getter
    private String email;

    @Column(name = "password_hash", length = 40, nullable = false)
    @Getter
    private String passwordHash;

    @Column(name = "is_active", nullable = false)
    @Getter
    private Boolean isActive;

    protected Salon() {}

    public Salon(CreateSalonCommand command) {
        this.imageUrl = command.imageUrl();
        this.location = command.location();
        this.phone = command.phone();
        this.email = command.email();
        this.passwordHash = command.passwordHash();
        this.isActive = Boolean.FALSE;

    }
}
