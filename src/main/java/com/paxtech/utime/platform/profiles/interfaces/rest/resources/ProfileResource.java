package com.paxtech.utime.platform.profiles.interfaces.rest.resources;

import java.util.List;
import java.util.Map;

public record ProfileResource(
        Long id,
        String userId,
        String salonName,
        Double rating,
        String profileImage,
        String coverImage,
        Map<String, String> socials,
        List<String> portfolioImages
        /*String address,
        String salonEmail,
        AccountResource accounts*/
) {
    public record AccountResource(
            String accountId,
            String email,
            String passwordHash,
            boolean isActive
    ) {}
}
