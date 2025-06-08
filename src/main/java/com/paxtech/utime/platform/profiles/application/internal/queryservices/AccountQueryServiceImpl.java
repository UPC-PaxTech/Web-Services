package com.paxtech.utime.platform.profiles.application.internal.queryservices;

import com.paxtech.utime.platform.profiles.domain.model.aggregates.Account;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateAccountCommand;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAccountByIdQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAccountByUserQuery;
import com.paxtech.utime.platform.profiles.domain.services.AccountQueryService;
import com.paxtech.utime.platform.profiles.infrastructure.persistence.jpa.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AccountQueryServiceImpl implements AccountQueryService {
    private final AccountRepository accountRepository;

    public AccountQueryServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> handle(GetAccountByIdQuery query){
        return accountRepository.findById(query.profileId());
    }

    @Override
    public Optional<Account> handle(GetAccountByUserQuery query){
        return accountRepository.findByUser_name(query.user());
    }
}
