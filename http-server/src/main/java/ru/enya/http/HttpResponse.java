package ru.enya.http;

import java.io.OutputStream;

public interface HttpResponse {

    void setCode(HttpStatusCode code);

    void addHeader(String name, String value);

    OutputStream getOutputStream();

}
