package test;

import api.BaseTest;
import api.util.UriCreator;
import endava.api.DataProvider.FirstProvider;
import endava.api.model.FirstReturnTypeModel;
import endava.api.rest.FirstRest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
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
    public void init() {
        firstRest = server.initialise(FirstRest.class);
    }

    @Test(dataProviderClass = FirstProvider.class, dataProvider = "postMethodDataProvider")
    //This is the example with data provider from the lecture
    public void test2(JSONObject jsonObject) {
        ResponseEntity<FirstReturnTypeModel> responseEntity = firstRest.firstPostRequest(jsonObject);
        Assert.assertNotNull(responseEntity); //You can make a different assertion here using data from the response
    }

    @Test
    public void firstTest() {
        executeCurrentMethodLog();
        ResponseEntity<FirstReturnTypeModel[]> firstReturnTypeModelResponseEntityList = firstRest.firstGetRequest();
        assertTrue(firstReturnTypeModelResponseEntityList.getStatusCode().equals(HttpStatus.OK), "Unsuccessful response!");
        log.info("Asserted response is with status: " + firstReturnTypeModelResponseEntityList.getStatusCode() + "!!!");
        List<FirstReturnTypeModel> modelList = Arrays.asList(firstReturnTypeModelResponseEntityList.getBody());
        assertEquals(modelList.get(0).getId(), 1, "ID from the first object is not as expected!!");
        assertEquals(modelList.get(0).getBody(), "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
        log.info("Successfully asserted values!");
    }

    @Test
    public void secondTest() {
        executeCurrentMethodLog();
        ResponseEntity<FirstReturnTypeModel> firstReturnTypeModelResponseEntity = firstRest.getObjectById(39);
        assertTrue(firstReturnTypeModelResponseEntity.getStatusCode().equals(HttpStatus.OK), "some kind of error. Not successful response! ");
        log.info("Asserted response is with status: " + firstReturnTypeModelResponseEntity.getStatusCode() + "!!!");
        FirstReturnTypeModel firstReturnTypeModel = firstReturnTypeModelResponseEntity.getBody();
        assertFalse(firstReturnTypeModel.getId() == 20, "Some things are not right!");
        assertEquals(firstReturnTypeModel.getId(), 39, "This is not the right ID");
        log.info("Successfully asserted values!");
    }

    @Test
    public void FirstPostTest() {
        executeCurrentMethodLog();
        final JSONObject postModel = new JSONObject();
        postModel.put("userId", new Long(3));
        postModel.put("id", new Long(101));
        postModel.put("title", "102102104");
        postModel.put("body", "body for 106");
        ResponseEntity<FirstReturnTypeModel> secondReturnTypeModelResponseEntity = firstRest.firstPostRequest(postModel);
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getStatusCode(), HttpStatus.CREATED);
        Assert.assertNotNull(secondReturnTypeModelResponseEntity.getBody());
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getBody().getId(), 101);
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getBody().getUserId(), 3);
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getBody().getTitle(), "102102104");
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getBody().getBody(), "body for 106");
    }

    @Test
    public void FirstPutTest() {
        executeCurrentMethodLog();
        final JSONObject postModel = new JSONObject();
        postModel.put("userId", new Long(8));
        postModel.put("id", new Long(4));
        postModel.put("title", "202102104");
        postModel.put("body", "body for 101");
        ResponseEntity<FirstReturnTypeModel> secondReturnTypeModelResponseEntity = firstRest.firstPutRequest(postModel,4);
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(secondReturnTypeModelResponseEntity.getBody());
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getBody().getId(), 4);
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getBody().getUserId(), 8);
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getBody().getTitle(), "202102104");
        Assert.assertEquals(secondReturnTypeModelResponseEntity.getBody().getBody(), "body for 101");
    }


    @Test
    public void FirstDeleteTest(){
        executeCurrentMethodLog();
        ResponseEntity<String> deleteReturnTypeModelResponseEntity=firstRest.firstDeleteRequest(4);
        Assert.assertEquals(deleteReturnTypeModelResponseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(deleteReturnTypeModelResponseEntity.getBody(),"{}");
    }


}
