package com.sebaahs.builderview.src.provides.preference;

public enum PreferencesKey{

    SESION("sesion"),
    EMAIL("email"),
    PASSWORD("password");

    private final String value;

    PreferencesKey(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
