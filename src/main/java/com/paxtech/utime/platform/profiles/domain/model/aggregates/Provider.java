package com.paxtech.utime.platform.profiles.domain.model.aggregates;
import com.paxtech.utime.platform.iam.domain.model.aggregates.User;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateProviderCommand;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.CompanyName;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Provider extends AuditableAbstractAggregateRoot<Provider> {

    @Embedded
    private CompanyName companyName;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true) // FK en tabla Provider
    private User user;


    protected Provider() {}

    public Provider(CreateProviderCommand command, User user) {
        this.companyName = new CompanyName(command.companyName());
        this.user = user;
    }


    public User getUser() { return user; }

    public String getCompanyName() {
        return companyName.getValue();
    }

}
