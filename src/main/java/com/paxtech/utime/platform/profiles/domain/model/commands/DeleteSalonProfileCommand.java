package com.paxtech.utime.platform.profiles.domain.model.commands;

public record DeleteSalonProfileCommand(Long id) {
    public DeleteSalonProfileCommand{
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id cannot be null or less than 1");
        }
    }
}
