package test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class Test01_GET {


    @Test
    void test01(){
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void test02(){
        try {
            given()
                    .get("https://reqres.in/api/users?page=2")
                    .then()
                    .statusCode(200)
                    .body("data.id",hasItem(8))
                    .log().all();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
