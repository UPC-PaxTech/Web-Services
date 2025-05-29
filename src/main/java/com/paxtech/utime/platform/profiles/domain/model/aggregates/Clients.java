package com.paxtech.utime.platform.profiles.domain.model.aggregates;
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
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private Date birth_date;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    private String password_hash;

    @Column(nullable = false)
    private Boolean is_active;

    protected Clients() {}
}
