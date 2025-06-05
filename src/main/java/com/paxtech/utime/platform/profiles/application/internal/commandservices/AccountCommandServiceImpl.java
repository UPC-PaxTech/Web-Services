package com.paxtech.utime.platform.profiles.application.internal.commandservices;

import com.paxtech.utime.platform.profiles.domain.model.entity.Account;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateAccountCommand;
import com.paxtech.utime.platform.profiles.domain.services.AccountCommandService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {
    private final AccountRepository accountRepository;

    public AccountCommandServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> handle(CreateAccountCommand command) {
        if (accountRepository.existsByUser_name(command.userName())){
            throw new IllegalArgumentException("Account with same username already exists");
        }

        var account = new Account(command);
        var createdAccount = accountRepository.save(account);
        return Optional.of(createdAccount);
    }
}
