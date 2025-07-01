package com.paxtech.utime.platform.profiles.domain.model.entities;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreatePortfolioInProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.UpdatePortfolioInProfileCommand;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class PortfolioInProfile extends AuditableAbstractAggregateRoot<PortfolioInProfile> {

    @Getter
    @Column(nullable = false)
    private Long portfolioId;

    @Getter
    @Column(nullable = false)
    private Long salonProfileId;

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
