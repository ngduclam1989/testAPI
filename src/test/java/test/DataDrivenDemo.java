package test;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DataDrivenDemo  extends DataForTests{



    @Test(dataProvider = "dataForTest")
    public static void test_POST_dataDriven(String name, String job) {
        JSONObject request = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("Content-Type", "application/json");

        request.put("name",name);
        request.put("job",job);
        baseURI ="https://reqres.in/";

        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .log().body();
    }


    @Parameters({"userID"})
    @Test
    public void test_Delete_dataDriven(int userID){
        baseURI ="https://reqres.in/";
        when().delete("/api/users/"+userID).then().statusCode(204).log().all();

    }

    @Test
    public void test_demo(){
        given().
                baseUri("https://api.postman.com").
                header("X-api-key","PMAK-669e15cd8a1b840001995ff9-8ee068f7d14560a91eec738a23153d7f25").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                log().body().
                body("workspaces[0].name",is(equalTo("My Workspace")),"workspaces[0].type",is(equalTo("personal")));
    }

    @Test
    public void extract_single_field_response(){
        String res1 = given().
                baseUri("https://api.postman.com").
                header("X-api-key","PMAK-669e15cd8a1b840001995ff9-8ee068f7d14560a91eec738a23153d7f25").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                log().body().
                extract().
                response().path("workspaces[0].name");
        System.out.println("name in response= "+res1);
        Assert.assertEquals(res1,"My Workspace");
    }

    @Test
    public void extract_multi_field_response(){
        Response res = given().
                baseUri("https://api.postman.com").
                header("X-api-key","PMAK-669e15cd8a1b840001995ff9-8ee068f7d14560a91eec738a23153d7f25").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
               log().body().
                extract().
                response();
        String res1 = res.path("workspaces[0].name");
        String res2 = res.path("workspaces[0].type");
        System.out.println("name in response= "+res1);
        System.out.println("type in response= "+res2);
        Assert.assertEquals(res1,"My Workspace");
        Assert.assertEquals(res2,"personal");
    }

}
