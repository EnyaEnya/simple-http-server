package ru.enya.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            }
        }
    }

}

