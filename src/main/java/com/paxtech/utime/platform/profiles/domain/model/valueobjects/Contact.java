package com.paxtech.utime.platform.profiles.domain.model.valueobjects;

public record Contact(String phone, String email) {
    public Contact() {
        this(null, null);
    }

    public Contact(String phone){
        this(phone, null);
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    

}
