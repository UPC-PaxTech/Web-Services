package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Clients;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.ClientResource;

public class ClientResourceFrontEntityAssembler {
    public static ClientResource toResourceFromEntity(Clients entity) {
        return new ClientResource(entity.getId(), entity.getName(), entity.getBirth_date(), entity.getPasswordHash(), entity.getIs_active());
    }
}
