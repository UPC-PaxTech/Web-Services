package com.paxtech.utime.platform.profiles.domain.model.aggregates;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateAccountCommand;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.*;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account extends AuditableAbstractAggregateRoot<Account> {
    @Embedded
    private Password password;

    @Embedded
    private ActiveStatus isActive;

    @Embedded
    private User user;

    public Account(String passwordhash, Boolean isActive, String userName) {
        this.password = new Password(passwordhash);
        this.isActive = new ActiveStatus(isActive);
        this.user = new User(userName);
    }

    public Account() {}

    public Account(CreateAccountCommand command) {
        this.password = new Password(command.password());
        this.isActive = new ActiveStatus(command.isActive());
        this.user = new User(command.userName());
    }

    public String getPasswordHash() {
        return password.passwordhash();
    }

    public String getUserName() {
        return user.name();
    }

    public boolean isActive() {
        return isActive.isActive();
    }

    public void updateName(String name){
        this.user = new User(name);
    }

    public void updatePassword(String password){
        this.password = new Password(password);
    }
}
