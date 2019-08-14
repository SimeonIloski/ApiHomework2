package endava.api.DataProvider;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

/**
 * @author stpetrovski on 8/7/2019
 * @project EndavaApi2
 */
public class FirstProvider {
    @DataProvider(name = "postMethodDataProvider")
    public static Object[][] putObject() {
        final JSONObject postModel = new JSONObject();
        postModel.put("userId", new Long(2));
        postModel.put("id", new Long(102));
        postModel.put("title", "102102102");
        postModel.put("body", "body for 102");
        final Object[][] returnObject = new Object[][]{{postModel}};
        return returnObject;
    }

}
