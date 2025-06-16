package com.paxtech.utime.platform.reservations.interfaces.rest;

import com.paxtech.utime.platform.reservations.domain.model.commands.CreateTimeSlotCommand;
import com.paxtech.utime.platform.reservations.domain.services.TimeSlotCommandService;
import com.paxtech.utime.platform.reservations.domain.services.TimeSlotQueryService;
import com.paxtech.utime.platform.reservations.interfaces.rest.resources.CreateTimeSlotResource;
import com.paxtech.utime.platform.reservations.interfaces.rest.resources.TimeSlotResource;
import com.paxtech.utime.platform.reservations.interfaces.rest.transform.CreateTimeSlotCommandFromResourceAssembler;
import com.paxtech.utime.platform.reservations.interfaces.rest.transform.TimeSlotResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/time-slots", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Time Slots", description = "Available Time Slots Endpoints")
public class TimeSlotController {
    private final TimeSlotCommandService timeSlotCommandService;
    private final TimeSlotQueryService timeSlotQueryService;

    public TimeSlotController(TimeSlotCommandService timeSlotCommandService, TimeSlotQueryService timeSlotQueryService) {
        this.timeSlotCommandService = timeSlotCommandService;
        this.timeSlotQueryService = timeSlotQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new Time Slot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TimeSlot created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<TimeSlotResource> createTimeSlot(@RequestBody CreateTimeSlotResource resource) {
        var createTimeSlotCommand = CreateTimeSlotCommandFromResourceAssembler.toCommandFromResource(resource);
        var timeSlot = timeSlotCommandService.handle(createTimeSlotCommand);
        if (timeSlot.isEmpty()) return ResponseEntity.badRequest().build();
        var createdTimeSlot = timeSlot.get();
        var timeSlotResource = TimeSlotResourceFromEntityAssembler.toResourceFromEntity(createdTimeSlot);
        return new ResponseEntity<>(timeSlotResource, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a Time Slot by ID")
    public ResponseEntity<TimeSlotResource> getTimeSlotById(@PathVariable Long id) {
        var query = new com.paxtech.utime.platform.reservations.domain.model.queries.GetTimeSlotByIdQuery(id);
        var result = timeSlotQueryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resource = TimeSlotResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    @Operation(summary = "Get all Time Slots")
    public ResponseEntity<Iterable<TimeSlotResource>> getAllTimeSlots() {
        var timeSlots = timeSlotQueryService.handle(new com.paxtech.utime.platform.reservations.domain.model.queries.GetAllTimeSlotsQuery());
        if (timeSlots.isEmpty()) return ResponseEntity.notFound().build();
        var resources = timeSlots.stream()
                .map(TimeSlotResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
