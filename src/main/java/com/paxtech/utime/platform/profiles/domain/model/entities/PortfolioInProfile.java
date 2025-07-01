package com.paxtech.utime.platform.profiles.domain.model.entities;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.PortfolioImage;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreatePortfolioInProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.UpdatePortfolioInProfileCommand;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class PortfolioInProfile extends AuditableAbstractAggregateRoot<PortfolioInProfile> {

    @Getter
    @Column(nullable = false)
    private Long portfolioId;

    @Getter
    @Column(nullable = false)
    private Long salonProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", insertable = false, updatable = false)
    private PortfolioImage portfolioImage;

    public PortfolioImage getPortfolioImage() {
        return portfolioImage;
    }

    // Constructor vacío requerido por JPA
    public PortfolioInProfile() {}

    // Constructor para creación
    public PortfolioInProfile(CreatePortfolioInProfileCommand command) {
        this.portfolioId = command.portfolioId();
        this.salonProfileId = command.salonProfileId();
    }

    // Método para actualizar
    public PortfolioInProfile update(UpdatePortfolioInProfileCommand command) {
        this.portfolioId = command.portfolioId();
        this.salonProfileId = command.salonProfileId();
        return this;
    }
}
