package com.paxtech.utime.platform.workers.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.interfaces.acl.ProviderContextFacade;
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
    private final ProviderContextFacade providerContextFacade;

    public WorkerCommandServiceImpl(WorkerRepository workerRepository, ProviderContextFacade providerContextFacade) {
        this.workerRepository = workerRepository;
        this.providerContextFacade = providerContextFacade;
    }

    @Override
    public Optional<Worker> handle(CreateWorkerCommand command){
        var workerName = new WorkerName(command.name());
        if (workerRepository.existsByNameAndProviderId(workerName, new ProviderId(command.salonId()))){
            throw new IllegalArgumentException("Worker with this name already exists");
        }
        if (providerContextFacade.fetchProviderById(command.salonId()).isEmpty()){
            throw new IllegalArgumentException("Provider with this id does not exist");
        }
        var worker = new Worker(command);
        workerRepository.save(worker);
        return Optional.of(worker);
    }
}
