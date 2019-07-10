package ru.enya.http;

import java.io.IOException;
import java.io.OutputStream;

public interface HttpResponse {

    void setCode(HttpStatusCode code);

    void addHeader(String name, String value);

    void writeHeaders() throws IOException;

    OutputStream getOutputStream();

}
