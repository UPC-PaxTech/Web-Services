package com.paxtech.utime.platform.profiles.domain.model.aggregates;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreatePortfolioImageCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.UpdatePortfolioImageCommand;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class PortfolioImage extends AuditableAbstractAggregateRoot<PortfolioImage> {

    @Getter
    @Column(nullable = false, length = 512)
    private String imageUrl;

    @Getter
    @Column(nullable = false)
    private Long portfolioId;

    // Default constructor for JPA
    public PortfolioImage() {}

    public PortfolioImage(CreatePortfolioImageCommand command) {
        if (command.portfolioId() == null || command.portfolioId() <= 0)
            throw new IllegalArgumentException("Portfolio ID must be a positive number.");
        if (command.imageUrl() == null || command.imageUrl().isBlank())
            throw new IllegalArgumentException("Image URL must not be null or blank.");

        this.portfolioId = command.portfolioId();
        this.imageUrl = command.imageUrl();
    }

    public PortfolioImage update(UpdatePortfolioImageCommand command) {
        if (command.imageUrl() == null || command.imageUrl().isBlank())
            throw new IllegalArgumentException("Image URL must not be null or blank.");

        this.imageUrl = command.imageUrl();
        return this;
    }
}
