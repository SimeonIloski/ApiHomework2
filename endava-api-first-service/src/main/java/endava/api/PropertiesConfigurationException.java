package endava.api;

import org.apache.commons.configuration.ConfigurationException;

public class PropertiesConfigurationException extends RuntimeException {
    public PropertiesConfigurationException(ConfigurationException e) {
        super(e);
    }
}
