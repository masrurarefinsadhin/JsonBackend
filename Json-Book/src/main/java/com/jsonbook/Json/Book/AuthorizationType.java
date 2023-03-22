package com.jsonbook.Json.Book;

public enum AuthorizationType {
    NO_AUTH("No Auth"),
    API_KEY("API Key"),
    BEARER_TOKEN("Bearer Token"),
    JWT_BEARER("JWT Bearer"),
    BASIC_AUTH("Basic Auth");
    public final String label;

    private AuthorizationType(String label) {
        this.label = label;
    }
}
