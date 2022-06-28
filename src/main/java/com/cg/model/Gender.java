package com.cg.model;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Gender getEnum(String value) {
        Gender[] values = values();
        for (Gender role : values) {
            if (role.getValue().equals(value))
                return role;
        }
        throw new IllegalArgumentException("Invalid: " + value);
    }
}
