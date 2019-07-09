package ru.enya.http;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

public interface HttpRequest {

    Method getMethod();

    String getRequestURI();

    String getHeader(String name);

    List<String> getHeaders(String name); //for example cookie values

    InputStream getInputStream();

    Collection<String> getHeaderNames(); //keys
}
