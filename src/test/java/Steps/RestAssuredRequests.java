package Steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class RestAssuredRequests {

    private static String requestBody = "{\n" +
            "  \"title\": \"foo\",\n" +
            "  \"body\": \"baz\",\n" +
            "  \"userId\": \"1\",\n" +
            "  \"id\": \"1\" \n}";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.trello.com/1";
    }

    @Test
    public void putRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body("")
                .when()
                .post("/lists?idBoard=61da76dc3cbb7d08d85c2f17&key=4040a9a3e24652d77ecb67da3090c926&token=6dee4961adb09f5e85fde8d7629416857c9b14fe00d370f28cebdff87ddc4eea&name=frontend23")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        //Assertions.assertEquals("fronted23", response.jsonPath().getString("name"));
      //  Assertions.assertEquals("baz", response.jsonPath().getString("body"));
      //  Assertions.assertEquals("1", response.jsonPath().getString("userId"));
     //   Assertions.assertEquals("1", response.jsonPath().getString("id"));
    }
}