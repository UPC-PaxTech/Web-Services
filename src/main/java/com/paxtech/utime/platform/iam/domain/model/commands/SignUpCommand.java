﻿package com.paxtech.utime.platform.iam.domain.model.commands;

/**
 * Sign up command
 * <p>
 *     This class represents the command to sign up a user.
 * </p>
 * @param email the email of the user
 * @param password the password of the user
 *
 * @see com.acme.center.platform.iam.domain.model.aggregates.User
 */

public record SignUpCommand(String email, String password) {
}
