package endava.api;



import api.Api;
import api.util.CommonConstants;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static api.util.CommonHelpers.executeCurrentMethodLog;

public class ApiDefinition extends Api {

    private String api;
    private PropertiesConfiguration configuration;
    protected Logger log = LoggerFactory.getLogger(getClass());

    protected RestTemplate restTemplate = new RestTemplate();

    protected HttpEntity<?> getHttpEntity() {
        executeCurrentMethodLog();
        httpRequestHeaders.setContentType(MediaType.APPLICATION_JSON);
        log.info("HttpEntity successfully prepared!");
        return new HttpEntity<Object>(httpRequestHeaders);
    }

    public ApiDefinition() {
        try {
            configuration = new PropertiesConfiguration(CommonConstants.COMMON_PROPERTIES_FILE_NAME);
        } catch (ConfigurationException e) {
            throw new PropertiesConfigurationException(e);
        }
        api = configuration.getString(CommonConstants.APP_SERVER_BASE_URL_PROPERTY_NAME);
    }

    public String getApi() {
        return api;
    }

    protected String getBaseURI() {
        return baseURI;
    }

}
