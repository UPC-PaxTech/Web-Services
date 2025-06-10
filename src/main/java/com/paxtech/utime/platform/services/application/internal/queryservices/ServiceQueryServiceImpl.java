package com.paxtech.utime.platform.services.application.internal.queryservices;

import com.paxtech.utime.platform.services.domain.exception.SalonNotFoundException;
import com.paxtech.utime.platform.services.domain.model.aggregates.Service;
import com.paxtech.utime.platform.services.domain.model.queries.GetAllServicesQuery;
import com.paxtech.utime.platform.services.domain.model.queries.GetServiceByIdQuery;
import com.paxtech.utime.platform.services.domain.model.queries.GetServicesBySalonIdQuery;
import com.paxtech.utime.platform.services.domain.services.ServiceQueryService;
import com.paxtech.utime.platform.services.infrastructure.persistence.jpa.repositories.ServiceRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
public class ServiceQueryServiceImpl implements ServiceQueryService {
    private final ServiceRepository serviceRepository;
    public ServiceQueryServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    @Override
    public List<Service> handle(GetAllServicesQuery query){
        return serviceRepository.findAll();
    }

    @Override
    public List<Service> handle(GetServicesBySalonIdQuery query){
        if(!serviceRepository.existsById(query.salonId().getSalonId())){
            throw new SalonNotFoundException(query.salonId().getSalonId());
        }
        return serviceRepository.findBySalonId(query.salonId());
    }

    @Override
    public Optional<Service> handle(GetServiceByIdQuery query){
        return serviceRepository.findById(query.Id());
    }
}
