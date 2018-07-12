package api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonHelpers {

    private static Logger log = LoggerFactory.getLogger(Class.class);

    public static void executeCurrentMethodLog() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        log.info("Executing Method : " + methodName);
    }
}
