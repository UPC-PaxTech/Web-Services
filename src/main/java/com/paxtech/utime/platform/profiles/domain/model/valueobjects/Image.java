package com.paxtech.utime.platform.profiles.domain.model.valueobjects;

public record Image(String url) {
    public Image() {
        this(null);
    }

    public String getUrl() {
        return url;
    }


}
