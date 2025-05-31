package com.paxtech.utime.platform.profiles.interfaces.rest.resources;

import java.util.Date;

public record ClientResource(Long id, String name, Date birth_date, String passwordHash, Boolean is_active) {
}
