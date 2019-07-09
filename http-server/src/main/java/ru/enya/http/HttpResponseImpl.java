package ru.enya.http;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {

    private HttpStatusCode code;
    private Map<String, List<String>> headers;
    private final OutputStream outputStream;

    public HttpResponseImpl(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.headers = new HashMap<>();
    }


    @Override
    public void setCode(HttpStatusCode code) {
        this.code = code;
    }

    @Override
    public void addHeader(String name, String value) {
        if (headers.containsKey(name)) {
            headers.get(name).add(value);
        } else {
            List<String> values = new ArrayList<>();
            values.add(value);
            headers.put(name, values);
        }
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
