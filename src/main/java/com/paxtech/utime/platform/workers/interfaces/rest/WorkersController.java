package com.paxtech.utime.platform.workers.interfaces.rest;

import com.paxtech.utime.platform.workers.domain.model.queries.GetAllWorkersQuery;
import com.paxtech.utime.platform.workers.domain.model.queries.GetWorkerByIdQuery;
import com.paxtech.utime.platform.workers.domain.services.WorkerCommandService;
import com.paxtech.utime.platform.workers.domain.services.WorkerQueryService;
import com.paxtech.utime.platform.workers.interfaces.rest.resources.CreateWorkerResource;
import com.paxtech.utime.platform.workers.interfaces.rest.resources.WorkerResource;
import com.paxtech.utime.platform.workers.interfaces.rest.transform.CreateWorkerCommandFromResourceAssembler;
import com.paxtech.utime.platform.workers.interfaces.rest.transform.WorkerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/workers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Workers", description = "Available Worker Endpoints")
public class WorkersController {
    private final WorkerCommandService workerCommandService;
    private final WorkerQueryService workerQueryService;

    public WorkersController(WorkerCommandService workerCommandService, WorkerQueryService workerQueryService) {
        this.workerCommandService = workerCommandService;
        this.workerQueryService = workerQueryService;
    }

    @PostMapping
    @Operation(summary = "Create new Worker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worker created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<WorkerResource> createWorker(@RequestBody CreateWorkerResource resource) {
        var createWorkerCommand = CreateWorkerCommandFromResourceAssembler.toCommandFromResource(resource);
        var worker = workerCommandService.handle(createWorkerCommand);
        if(worker.isEmpty()) return ResponseEntity.badRequest().build();
        var createdWorker = worker.get();
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(createdWorker);
        return new ResponseEntity<>(workerResource, HttpStatus.CREATED);
    }

    @GetMapping("/{workerId}")
    @Operation(summary = "Get a worker by ID")
    public ResponseEntity<WorkerResource> getWorkerById(@PathVariable Long workerId) {
        var query = new GetWorkerByIdQuery(workerId);
        var result = workerQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resource = WorkerResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    @Operation(summary = "Get all workers")
    public ResponseEntity<List<WorkerResource>> getAllWorkers() {
        var workers = workerQueryService.handle(new GetAllWorkersQuery());
        if (workers.isEmpty()) return ResponseEntity.notFound().build();
        var resources = workers.stream()
                .map(WorkerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

}
