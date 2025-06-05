package com.paxtech.utime.platform.profiles.domain.model.valueobjects;

public record ActiveStatus(boolean isActive) {

    public ActiveStatus() {
        this(true);
    }

    public boolean getStatus(){
        return isActive;
    }

}
