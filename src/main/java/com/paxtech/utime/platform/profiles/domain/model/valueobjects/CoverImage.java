package com.paxtech.utime.platform.profiles.domain.model.valueobjects;

public record CoverImage(String url) {
    public CoverImage(){
        this(null);
    }

    public String getUrl() {
        return url;
    }
}
