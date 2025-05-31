package com.paxtech.utime.platform.profiles.domain.model.aggregates;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateClientCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Clients extends AbstractAggregateRoot<Clients> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    @Getter

    private Long id;

    @Column(nullable = false)
    @Getter
    private Date birth_date;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String passwordHash;

    @Column(nullable = false)
    @Getter
    private Boolean is_active;

    protected Clients() {}

    public Clients(CreateClientCommand command) {
        this.birth_date = command.birthDate();
        this.name = command.name();
        this.passwordHash = command.passwordHash();
        this.is_active = Boolean.FALSE;
    }
}
