package com.paxtech.utime.platform.workers.application.internal.commandservices;

import com.paxtech.utime.platform.workers.domain.model.aggregates.Worker;
import com.paxtech.utime.platform.workers.domain.model.commands.CreateWorkerCommand;
import com.paxtech.utime.platform.workers.domain.model.valueobjects.ProviderId;
import com.paxtech.utime.platform.workers.domain.model.valueobjects.WorkerName;
import com.paxtech.utime.platform.workers.domain.services.WorkerCommandService;
import com.paxtech.utime.platform.workers.infrastructure.persistence.jpa.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerCommandServiceImpl implements WorkerCommandService {
    private final WorkerRepository workerRepository;

    public WorkerCommandServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Optional<Worker> handle(CreateWorkerCommand command){
        var workerName = new WorkerName(command.name());
        if (workerRepository.existsByNameAndProviderId(workerName, new ProviderId(command.salonId()))){
            throw new IllegalArgumentException("Worker with this name already exists");
        }
        var worker = new Worker(command);
        workerRepository.save(worker);
        return Optional.of(worker);
    }
}
