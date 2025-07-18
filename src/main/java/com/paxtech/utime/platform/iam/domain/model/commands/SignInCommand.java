package com.paxtech.utime.platform.iam.domain.model.commands;


/**
 * Sign in command
 * <p>
 *     This class represents the command to sign in a user.
 * </p>
 * @param email the email of the user
 * @param password the password of the user
 *
 * @see com.acme.center.platform.iam.domain.model.aggregates.User
 */
public record SignInCommand(String email, String password) {
}