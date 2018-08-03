package endava.api.rest;

import endava.api.ApiDefinition;
import endava.api.model.FirstModel;
import endava.api.model.FirstReturnTypeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import static api.util.CommonHelpers.executeCurrentMethodLog;

public class FirstRest extends ApiDefinition {

    private static Logger log = LoggerFactory.getLogger(FirstRest.class);

    private static final String URI_FOR_TEST_API = "/posts";
    private static final String URI_FOR_TEST_API_TWO = "/posts/{id}";

    public ResponseEntity<FirstReturnTypeModel[]> firstGetRequest(){
        executeCurrentMethodLog();
        HttpEntity<?> httpEntity = getHttpEntity();
        ResponseEntity<FirstReturnTypeModel[]> response;
        try {
            response = restTemplate.exchange(getBaseURI() + /*getApi() +*/ URI_FOR_TEST_API, HttpMethod.GET, httpEntity, FirstReturnTypeModel[].class);
            return response;
        } catch (HttpClientErrorException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            log.info("Unhandled Exception! ");
        }
        return null;
    }

    public ResponseEntity<FirstReturnTypeModel> getObjectById(int id){
        executeCurrentMethodLog();
        HttpEntity<?> httpEntity1 = getHttpEntity();
        ResponseEntity<FirstReturnTypeModel>firstReturnTypeModelResponseEntity;
        try{
            firstReturnTypeModelResponseEntity = restTemplate.exchange(getBaseURI() + URI_FOR_TEST_API_TWO, HttpMethod.GET, httpEntity1,FirstReturnTypeModel.class,id);
            return firstReturnTypeModelResponseEntity;
        }catch (HttpClientErrorException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error(" any other kind of exc.");
        }
        return null;
    }

    protected HttpEntity<?> getHttpEntity() {
        executeCurrentMethodLog();
        httpRequestHeaders.setContentType(MediaType.APPLICATION_JSON);
        log.info("HttpEntity successfully prepared!");
        return new HttpEntity<Object>(httpRequestHeaders);
    }

}
