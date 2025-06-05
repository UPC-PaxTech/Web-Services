package com.paxtech.utime.platform.profiles.domain.services;

import com.paxtech.utime.platform.profiles.domain.model.entity.Account;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateAccountCommand;

import java.util.Optional;

public interface AccountCommandService {
    Optional<Account> handle(CreateAccountCommand command);
}
