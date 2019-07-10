package ru.enya.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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

    @Override
    public void writeHeaders() throws IOException {
        String resultString = "";
        resultString += "HTTP/1.1 " + code.getCode() + " " + code.getDescription() + "\r\n";
        Set<Map.Entry<String, List<String>>> set = headers.entrySet();
        for (Map.Entry<String, List<String>> entry: set) {
            resultString += entry.getKey() + ": " + entry.getValue().get(0) + "\r\n";
        }
        resultString += "\r\n";
        outputStream.write(resultString.getBytes());
        outputStream.flush();
    }

}
