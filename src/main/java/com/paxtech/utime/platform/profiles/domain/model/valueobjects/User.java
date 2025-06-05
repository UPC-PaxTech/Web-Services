package com.paxtech.utime.platform.profiles.domain.model.valueobjects;

public record User(String name) {
    public User(){
        this(null);
    }

    public User{
        if(name == null|| name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
}
