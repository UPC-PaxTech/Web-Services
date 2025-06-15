package com.paxtech.utime.platform.profiles.interfaces.rest.controllers;
import com.paxtech.utime.platform.profiles.domain.model.entity.Account;
import com.paxtech.utime.platform.profiles.domain.services.AccountCommandService;
import com.paxtech.utime.platform.profiles.domain.services.AccountQueryService;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.AccountResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.resources.CreateAccountResource;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.AccountResourceFromEntityAssembler;
import com.paxtech.utime.platform.profiles.interfaces.rest.transform.CreateAccountCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/accounts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Accounts", description = "Endpoints for accounts")
public class AccountsController {
    private final AccountCommandService accountCommandService;
    private final AccountQueryService accountQueryService;

    public AccountsController(AccountCommandService accountCommandService, AccountQueryService accountQueryService) {
        this.accountCommandService = accountCommandService;
        this.accountQueryService = accountQueryService;
    }


    @Operation(
            summary = "Create an account",
            description = "Creates an account with the provided password and username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorite source created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<AccountResource> createAccount(@RequestBody CreateAccountResource resource) {
        Optional<Account> account = accountCommandService.handle(CreateAccountCommandFromResourceAssembler.toCommandFromResource(resource));

        return account.map(a ->new ResponseEntity<>(AccountResourceFromEntityAssembler.toResourceFromEntity(a),CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
