package api.util;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class UriCreator {
    private Logger log = LoggerFactory.getLogger(UriCreator.class);
    private String propertiesFileName;

    public UriCreator(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    private String createUri(String relativePath) {
        String apiUrl = "";
        try {
            CompositeConfiguration configuration = new CompositeConfiguration();
            configuration.addConfiguration(new SystemConfiguration());
            configuration.addConfiguration(new PropertiesConfiguration(propertiesFileName));

            String scheme = configuration.getString(CommonConstants.APP_SERVER_PROTOCOL_PROPERTY_NAME);
            String host = configuration.getString(CommonConstants.APP_SERVER_HOST_PROPERTY_NAME);
            int port = configuration.getInt(CommonConstants.APP_SERVER_PORT_PROPERTY_NAME);
            URI uri = new URIBuilder().setScheme(scheme).setHost(host).setPort(port).build();
            apiUrl = uri.toString();
        } catch (URISyntaxException e) {
            log.debug("Could not get the properties from file %s, using the default value %s",
                    CommonConstants.COMMON_PROPERTIES_FILE_NAME, CommonConstants.DEFAULT_LOCAL_URL);
            apiUrl = CommonConstants.DEFAULT_LOCAL_URL;
        } catch (ConfigurationException e) {
            log.debug("Could not get the properties from file %s, using the default value %s",
                    CommonConstants.COMMON_PROPERTIES_FILE_NAME, CommonConstants.DEFAULT_LOCAL_URL);
            apiUrl = CommonConstants.DEFAULT_LOCAL_URL;
        }
        apiUrl = relativePath != null ? apiUrl + relativePath : apiUrl;
        return apiUrl;
    }

    public String createUri() {
        return createUri(null);
    }
}
