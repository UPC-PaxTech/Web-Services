package com.paxtech.utime.platform.profiles.domain.model.aggregates;
import com.paxtech.utime.platform.profiles.domain.model.commands.CreateSalonCommand;
import com.paxtech.utime.platform.profiles.domain.model.entity.Account;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.Contact;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.Image;
import com.paxtech.utime.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.paxtech.utime.platform.profiles.domain.model.valueobjects.Location;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Salon extends AuditableAbstractAggregateRoot<Salon> {

    @Embedded
    private Image imageUrl;

    @Embedded
    private Location location;

    @Embedded
    private Contact salonContact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Salon(String imgUrl,String street, String city, String postalCode, String country, String phone, String email) {
        this.imageUrl = new Image(imgUrl);
        this.location = new Location(street, city, postalCode, country);
        this.salonContact = new Contact(phone, email);

    }

    protected Salon() {}

    public Salon(CreateSalonCommand command) {
        this.imageUrl = new Image(command.imageUrl());
        this.location = new Location(command.street(), command.city(), command.postalCode(), command.country());
        this.salonContact = new Contact(command.email(), command.phone());
    }

    public String getImageUrl() {
        return imageUrl.getUrl();
    }
    public String getStreet() {
        return location.street();
    }
    public String getCity() {
        return location.city();
    }
    public String getPostalCode() {
        return location.postalCode();
    }
    public String getCountry() {
        return location.country();
    }
    public String getPhone() {
        return salonContact.getPhone();
    }
    public String getEmail() {
        return salonContact.getEmail();
    }
}
