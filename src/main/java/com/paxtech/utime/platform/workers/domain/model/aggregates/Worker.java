package com.paxtech.utime.platform.workers.domain.model.aggregates;



import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.paxtech.utime.platform.workers.domain.model.commands.CreateWorkerCommand;
import com.paxtech.utime.platform.workers.domain.model.valueobjects.WorkerName;
import com.paxtech.utime.platform.workers.domain.model.valueobjects.WorkerPhotoUrl;
import com.paxtech.utime.platform.workers.domain.model.valueobjects.WorkerSpecialization;
import com.paxtech.utime.platform.workers.domain.model.valueobjects.ProviderId;
import jakarta.persistence.*;

@Entity
public class Worker extends AuditableAbstractAggregateRoot<Worker> {

    @Embedded
    private WorkerName name;

    @Embedded
    private WorkerSpecialization specialization;

    @Embedded
    private WorkerPhotoUrl photoUrl;

    @Embedded
    private ProviderId providerId;

    public Worker(WorkerName name, WorkerSpecialization specialization, WorkerPhotoUrl photoUrl, ProviderId providerId) {
        this.name = name;
        this.specialization = specialization;
        this.photoUrl = photoUrl;
        this.providerId = providerId;
    }

    public Worker(CreateWorkerCommand command) {
        this.name = new WorkerName(command.name());
        this.specialization = new WorkerSpecialization(command.specialization());
        this.photoUrl = new WorkerPhotoUrl(command.photoUrl());
        this.providerId = new ProviderId(command.salonId());
    }

    public Worker() {}

    public String getName() {
        return name.name();
    }

    public String getSpecialization() {
        return specialization.specialization();
    }

    public String getPhotoUrl() {
        return photoUrl.url();
    }

    public Long getSalonId() {
        return providerId.providerId();
    }
}
