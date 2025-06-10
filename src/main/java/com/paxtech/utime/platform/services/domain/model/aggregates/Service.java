package com.paxtech.utime.platform.services.domain.model.aggregates;

import com.paxtech.utime.platform.services.domain.model.commands.CreateServiceCommand;
import com.paxtech.utime.platform.services.domain.model.valueobjects.*;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jdk.jshell.Snippet;
import lombok.Getter;

@Entity
public class Service extends AuditableAbstractAggregateRoot<Service> {
    @Embedded @Getter
    Name name;

    @Embedded @Getter
    Duration duration;

    @Embedded @Getter
    Price price;

    @Embedded @Getter
    Status status;

    @Embedded @Getter
    SalonId salonId;

    public Service() {}

    public Service(CreateServiceCommand command) {
        this.name = new Name(command.name());
        this.duration = new Duration(command.duration());
        this.price = new Price(command.price());
        this.status = new Status(command.status());
        this.salonId = new SalonId(command.salonId());
    }
}
