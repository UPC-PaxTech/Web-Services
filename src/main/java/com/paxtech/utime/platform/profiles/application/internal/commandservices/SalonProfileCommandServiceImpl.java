package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.SalonProfile;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.DeleteSalonProfileCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.UpdateSalonProfileCommand;
import com.paxtech.utime.platform.profiles.domain.services.SalonProfileCommandService;
import com.paxtech.utime.platform.profiles.domain.services.SalonProfileQueryService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.SalonProfileRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SalonProfileCommandServiceImpl implements SalonProfileCommandService {
    private final SalonProfileRepository salonProfileRepository;
    public SalonProfileCommandServiceImpl(SalonProfileRepository salonProfileRepository) {
        this.salonProfileRepository = salonProfileRepository;
    }
    @Override
    public Optional<SalonProfile> handle(CreateSalonProfileCommand command) {
        var salonProfile = salonProfileRepository.save(new SalonProfile(command));
        var createdSalonProfile = salonProfileRepository.save(salonProfile);
        return Optional.of(createdSalonProfile);
    }

    /*Falta Update y delete*/
    @Override
    public void handle(DeleteSalonProfileCommand command) {
        if(!salonProfileRepository.existsById(command.id())){
            throw new IllegalArgumentException("Service with this id does not exist");
        }
        try {
            salonProfileRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting service", e);
        }
    }

    @Override
    public Optional<SalonProfile> handle(UpdateSalonProfileCommand command) {
        if(!salonProfileRepository.existsById(command.id())){
            throw new IllegalArgumentException("Salon Profile with this id does not exist");
        }
        var result = salonProfileRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Salon Profile with this id does not exist");
        }
        var serviceToUpdate = result.get();
        try {
            var updatedService = salonProfileRepository.save(serviceToUpdate.updateInformation(command.profileUrl(), command.coverUrl()));
            return Optional.of(updatedService);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while updating service", e);
        }
    }
}
