package test;

import api.BaseTest;
import endava.api.model.FirstReturnTypeModel;
import endava.api.rest.FirstRest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static api.util.CommonHelpers.executeCurrentMethodLog;

public class FirstTest extends BaseTest {

    private FirstRest firstRest;

    @BeforeClass
    public void init(){
        firstRest = server.initialise(FirstRest.class);
    }

    @Test
    public void firstTest(){
        executeCurrentMethodLog();
        FirstReturnTypeModel firstReturnTypeModel = firstRest.firstGetRequest();

    }
}
