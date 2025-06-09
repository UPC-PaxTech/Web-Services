package com.paxtech.utime.platform.profiles.domain.model.aggregates;

import com.paxtech.utime.platform.profiles.domain.model.commands.CreateClientCommand;
import com.paxtech.utime.platform.profiles.domain.model.entity.Account;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.*;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Client extends AuditableAbstractAggregateRoot<Client> {

    @Embedded
    private FullName fullName;

    @Embedded
    private ClientBirthDate birthDate;

    @Embedded
    private Contact contact;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Client(String firstName, String lastName, String email, String phone, LocalDate birthDate) {
        this.fullName = new FullName(firstName, lastName);
        this.contact = new Contact(phone, email);
        this.birthDate = new ClientBirthDate(birthDate);
    }

    public Client(CreateClientCommand command) {
        this.fullName = new FullName(command.firstName(), command.lastName());
        this.contact = new Contact(command.phone(), command.email());
        this.birthDate = new ClientBirthDate(command.birthDate());
    }

    protected Client() {}

    public String getFirstName() {
        return fullName.getFirstName();
    }

    public String getLastName() {
        return fullName.getLastName();
    }

    public String getFullName() {
        return fullName.getFullName();
    }

    public LocalDate getBirthDate() {
        return birthDate.getBirthDate();
    }

    public String getPhone() {
        return contact.getPhone();
    }

    public String getEmail() {
        return contact.getEmail();
    }
}
