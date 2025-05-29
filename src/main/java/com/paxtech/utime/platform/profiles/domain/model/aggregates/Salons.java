package com.paxtech.utime.platform.profiles.domain.model.aggregates;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Salons extends AbstractAggregateRoot<Salons> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salon_id")
    @Getter

    private Long id;

    @Column(name = "image_url", length = 150, nullable = false)
    @Getter
    private String imageUrl;

    @Column(length = 30, nullable = false)
    @Getter
    private String location;

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

    protected Salons() {}

    public Salons(CreateSalonCommand command) {
        this.imageUrl = command.imageUrl();
        this.location = command.location();
        this.phone = command.phone();
        this.email = command.email();
        this.passwordHash = command.passwordHash();
        this.isActive = Boolean.FALSE;

    }
}
