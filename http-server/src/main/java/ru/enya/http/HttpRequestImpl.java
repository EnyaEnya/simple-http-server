package ru.enya.http;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {

    private final Method method;
    private final String requestURI;
    private final InputStream inputStream;

    private Map<String, List<String>> headers;


    public HttpRequestImpl(Method method, String requestURI, InputStream inputStream) {
        this.method = method;
        this.requestURI = requestURI;
        this.inputStream = inputStream;
        this.headers = new HashMap<>();
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
}
