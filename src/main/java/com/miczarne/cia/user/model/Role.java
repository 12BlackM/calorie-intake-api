package com.miczarne.cia.user.model;

import lombok.Getter;

@Getter
public enum Role {
    USER("APP_USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
