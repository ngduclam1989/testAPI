package test;

import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class RequestParameters {

    @Test
    public void single_query_parameters(){

        given()
                .baseUri("https://postman-echo.com")
//                .param("foo1", "bar1")
                .queryParam("foo1", "bar1")
                .log().all()
        .when()
                .get("/get")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }


    @Test
    public void multi_query_parameters(){
        HashMap<String,String> params = new HashMap<>();
        params.put("foo1", "bar1");
        params.put("foo2", "bar2");

        given()
                .baseUri("https://postman-echo.com")
//                .param("foo1", "bar1")
//                .queryParam("foo1", "bar1")
//                .queryParam("foo2", "bar2")
                .queryParams(params)
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

}



