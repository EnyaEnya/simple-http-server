package ru.enya.http;

public class HttpRequest {

    private final Method method;
    private final String requestURI;


    public HttpRequest(Method method, String requestURI) {
        this.method = method;
        this.requestURI = requestURI;
    }

    public Method getMethod() {
        return method;
    }

    public String getRequestURI() {
        return requestURI;
    }
}
