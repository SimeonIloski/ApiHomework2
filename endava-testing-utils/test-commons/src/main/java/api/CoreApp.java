package api;

public class CoreApp extends Api {

    public <T extends Api> T initialise(Class<T> clazz) {
        return getApiInstance(clazz);
    }

    private CoreApp addCookies(Api api) {
        api.setCookies(this.cookies);
        return this;
    }

    private CoreApp addHeader(Api api) {
        api.addHeaders(this.httpRequestHeaders);
        return this;
    }

    private CoreApp addConfig(Api api) {
        api.setBasePath(this.basePath);
        api.setBaseURI(this.baseURI);
        api.setPort(this.port);
        return this;
    }

    protected <T extends Api> T getApi(Class<T> clazz) {

        return getApiInstance(clazz);
    }

    private <T extends Api> T getApiInstance(Class<T> clazz) {
        try {
            Api api = clazz.newInstance();
            addCookies(api);
            addConfig(api);
            addHeader(api);
            return clazz.cast(api);
        } catch (Exception e) {
            throw new RuntimeException("Can't create api class");
        }
    }

}
