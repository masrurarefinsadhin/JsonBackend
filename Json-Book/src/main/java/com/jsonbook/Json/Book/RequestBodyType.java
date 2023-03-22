package com.jsonbook.Json.Book;

public enum RequestBodyType {
    NONE("none"),
    RAW("raw"),
    FORM_DATA("form-data"),
    FORM_ENCODED("x-www-form-urlencoded");
    public final String label;

    private RequestBodyType(String label) {
        this.label = label;
    }
}
