package com.paxtech.utime.platform.profiles.interfaces.rest.transform;

import com.paxtech.utime.platform.profiles.domain.model.entity.Account;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.AccountResource;

public class AccountResourceFromEntityAssembler {
    public static AccountResource toResourceFromEntity(Account entity){
        return new AccountResource(entity.getId(), entity.getPasswordHash(), entity.isActive(), entity.getUserName());
    }
}
