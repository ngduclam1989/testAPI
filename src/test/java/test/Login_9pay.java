package test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;


public class Login_9pay {

    public static String token;
    public static String phoneNum = "0347152468";


    public String getAuthToken() {

        String request = "{\n" +
                "    \"phone\":\""+phoneNum+"\",\n" +
                "    \"password\":\"123123\"\n" +
                "}";
        given()
                .baseUri("https://stg-be.wallet.9pay.mobi")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post("/v6/login/verify")
                .then()
                .log().body()
        ;
        String  requestLoginSuccess="{\n" +
                "    \"phone\":\""+phoneNum+"\",\n" +
                "    \"password\":\"123123\",\n" +
                "    \"otp\":\"123456\"\n" +
                "}";

        Response res = given()
                .baseUri("https://stg-be.wallet.9pay.mobi")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestLoginSuccess)
                .when()
                .post("/v6/login")
                .then()
                .log().body()
                .extract()
                .response();
//        System.out.println(res);
        String accessToken = res.path("data.access_token");
        System.out.println("access token: " + accessToken);
        return accessToken;

    }
    @BeforeTest
    public void login_request_9pay(){
        token = "Bearer " + getAuthToken();
        System.out.println("token: " + token);
    }


    @Test
    public void userinfo_9pay() {

        String  requestOrder = "{\"product_id\": \"21\",\"quality\":\"1\"}";

        given()
                .baseUri("https://stg-be.wallet.9pay.mobi")
                .header("Authorization",  token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestOrder)
                .log().all()
                .when()
                .post("/card/order")
                .then()
                .log().all()
        ;





    }
}
