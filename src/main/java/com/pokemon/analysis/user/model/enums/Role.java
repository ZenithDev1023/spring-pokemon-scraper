package com.pokemon.analysis.user.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER("User", ""),
    ADMIN("Admin", ""),
    DEV("Dev", ""),
    PM("Pm", ""),
    TESTER("Tester", "");

    private String type;
    private String description;

    Role(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
