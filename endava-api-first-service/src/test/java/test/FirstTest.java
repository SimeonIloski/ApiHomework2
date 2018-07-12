package test;

import api.BaseTest;
import api.util.UriCreator;
import endava.api.model.FirstReturnTypeModel;
import endava.api.rest.FirstRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

import static api.util.CommonHelpers.executeCurrentMethodLog;
import static org.testng.Assert.*;

public class FirstTest extends BaseTest {

    private static Logger log = LoggerFactory.getLogger(FirstTest.class);

    private FirstRest firstRest;

    @BeforeClass
    public void init(){
        firstRest = server.initialise(FirstRest.class);
    }

    @Test
    public void firstTest(){
        executeCurrentMethodLog();
        ResponseEntity<FirstReturnTypeModel[]> firstReturnTypeModelResponseEntityList = firstRest.firstGetRequest();
        assertTrue(firstReturnTypeModelResponseEntityList.getStatusCode().equals(HttpStatus.OK),"Unsuccessful response!");
        log.info("Asserted response is with status: " +firstReturnTypeModelResponseEntityList.getStatusCode() +"!!!");
        List<FirstReturnTypeModel> modelList = Arrays.asList(firstReturnTypeModelResponseEntityList.getBody());
        assertEquals(modelList.get(0).getId(),2,"ID from the first object is not as expected!!");
        assertEquals(modelList.get(0).getBody(),"quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
        log.info("Successfully asserted values!");
    }
}
