package api;

import api.util.CommonConstants;
import api.util.UriCreator;

public class BaseTest{

    protected CoreApp server = api(CommonConstants.COMMON_PROPERTIES_FILE_NAME);

    protected CoreApp api(String propertyFileName) {
        CoreApp api = new CoreApp();
        UriCreator uriCreator = new UriCreator(propertyFileName);
        String apiUrl = uriCreator.createUri();
        api.setBaseURI(apiUrl);
        return api;
    }
}
