package com.paxtech.utime.platform.profiles.domain.model.commands;

public record CreateAccountCommand(String userName, String password, Boolean isActive) {

}
