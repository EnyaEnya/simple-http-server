package ru.enya.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class HttpRequestImpl implements HttpRequest {

    private Method method;
    private String requestURI;
    private final InputStream inputStream;

    private Map<String, List<String>> headers;


    public HttpRequestImpl(InputStream inputStream) throws Throwable {
        this.inputStream = inputStream;
        this.headers = new HashMap<>();
        readInputHeaders();
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public String getRequestURI() {
        return requestURI;
    }

    @Override
    public String getHeader(String name) {
        return headers.get(name).get(0);//npe
    }

    @Override
    public List<String> getHeaders(String name) {
        return headers.get(name);
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return headers.keySet();
    }

    private void addHeader(String name, String value) {
        if (headers.containsKey(name)) {
            headers.get(name).add(value);
        } else {
            List<String> values = new ArrayList<>();
            values.add(value);
            headers.put(name, values);
        }
    }


    private void readInputHeaders() throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream));
        while(true) {
            String s = br.readLine();
            if(s == null || s.trim().length() == 0)  {
                break;
            }
            if (method == null && s.endsWith("HTTP/1.1")) {
                String[] httpString = s.split(" ");
                method = Method.valueOf(httpString[0]);
                requestURI = httpString[1];
            } else {
                String[] headers = s.split(": ");
                addHeader(headers[0], headers[1]);
            }
        }
    }

}

