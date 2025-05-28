package com.paxtech.utime.platform.news.domain.model.aggregates;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Salons extends AbstractAggregateRoot<Salons> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salon_id")
    private Long id;

    @Column(name = "image_url", length = 150, nullable = false)
    private String imageUrl;

    @Column(length = 30, nullable = false)
    private String location;

    @Column(length = 9, nullable = false)
    private String phone;

    @Column(length = 25, nullable = false)
    private String email;

    @Column(name = "password_hash", length = 40, nullable = false)
    private String passwordHash;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    protected Salons() {}

}
