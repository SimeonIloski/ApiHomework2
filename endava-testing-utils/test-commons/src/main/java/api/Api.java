package api;

import org.springframework.http.HttpHeaders;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Api {

    protected String baseURI = "";
    protected int port = -1;
    protected String basePath = "";
    protected HttpHeaders httpRequestHeaders = new HttpHeaders();
    protected Map<String, String> cookies = new LinkedHashMap<String, String>();
    protected Map<String, String> headers = new LinkedHashMap<String, String>();
    protected Map<String, String> params = new LinkedHashMap<String, String>();
    private boolean useCookies = true;

    public Api() {
    }

    public Api setBaseURI(String baseURI) {
        this.baseURI = baseURI;
        return this;
    }

    public Api setPort(int port) {
        this.port = port;
        return this;
    }

    public Api setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public Api doNotUseStoredCookies() {
        this.useCookies = false;
        return this;
    }

    public Api mergeCookies(Map<String, String> cookies) {
        this.cookies.putAll(cookies);
        return this;
    }

    public Api addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public Api addHeaders(HttpHeaders httpRequestHeaders) {
        this.httpRequestHeaders = httpRequestHeaders;
        return this;
    }

    public Api getHttpHeader(HttpHeaders httpRequestHeaders) {
        this.httpRequestHeaders = httpRequestHeaders;
        return this;
    }

    public Api setHttpHeaders(Map<String, List<String>> headers) {
        List<String> strings = headers.get(HttpHeaders.SET_COOKIE);
        String token = null;
        for (String string : strings) {
            if (string.startsWith(HttpHeaders.AUTHORIZATION)) {
                token = string;
                continue;
            }
            this.httpRequestHeaders.add(HttpHeaders.COOKIE, string);
        }
        if (token == null) {
            System.out.println("Token value is Null!");
        }
        this.httpRequestHeaders.add(HttpHeaders.COOKIE, token);
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (HttpHeaders.SET_COOKIE.equals(entry.getKey())) {
                continue;
            }
            this.httpRequestHeaders.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public Api addParam(String key, String value) {
        this.params.put(key, value);
        return this;
    }
    public HttpHeaders getRequestHeaders(){
        return this.httpRequestHeaders;
    }

    public Map<String, String> getCookies() {
        return this.cookies;
    }

    public Api setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
        return this;
    }
}
