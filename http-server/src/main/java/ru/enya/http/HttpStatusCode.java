package ru.enya.http;

public enum HttpStatusCode {

    OK(200, "OK"),
    NOT_FOUND(404, "Bad Request");

    private final int code;

    private final String description;

    HttpStatusCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
