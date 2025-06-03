package com.paxtech.utime.platform.profiles.domain.services;
import com.paxtech.utime.platform.profiles.domain.model.aggregates.Account;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAccountByIdQuery;
import com.paxtech.utime.platform.profiles.domain.model.queries.GetAccountByUserQuery;

import java.util.Optional;
public interface AccountQueryService {
    Optional<Account> handle(GetAccountByIdQuery command);
    Optional<Account> handle(GetAccountByUserQuery command);
}
