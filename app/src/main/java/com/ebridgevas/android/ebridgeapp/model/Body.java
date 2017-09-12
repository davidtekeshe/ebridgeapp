package com.ebridgevas.android.ebridgeapp.model;

public class Body {
    private final String text;
    private final String language;

    public Body(String text, String language) {
        this.text = text;
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public String getLanguage() {
        return language;
    }
}
