package test;

import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Test02_API {

    @Test
    public void test_1_Post()
    {
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("name","Lam Nguyen");
//        map.put("job","Teacher");
//        System.out.println(map);
        JSONObject request = new JSONObject();
        JSONObject header = new JSONObject();

        header.put("Content-Type", "application/json");

        request.put("name","Lam Nguyen");
        request.put("job","Teacher");

        System.out.println(request);
        System.out.println( request.toJSONString());

        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
//                .log().headers()
                .log().body();

    }


    @Test
    public void test_2_Put()
    {
        JSONObject request = new JSONObject();
        JSONObject header = new JSONObject();

        header.put("Content-Type", "application/json");

        request.put("name","Lam Nguyen");
        request.put("job","Teacher");

        System.out.println(request);
        System.out.println( request.toJSONString());

        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
//                .log().headers()
                .log().body();

    }

    @Test
    public void test_3_Patch()
    {
        JSONObject request = new JSONObject();
        JSONObject header = new JSONObject();

        header.put("Content-Type", "application/json");

        request.put("name","Lam Nguyen");
        request.put("job","Pirates");

        System.out.println(request);
        System.out.println( request.toJSONString());
        baseURI ="https://reqres.in/";

        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("/api/users/7")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void test_4_Delete(){
        baseURI ="https://reqres.in/";
        when().delete("/api/users/2").then().statusCode(204).log().all();

    }

}
