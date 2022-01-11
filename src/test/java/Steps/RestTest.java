package Steps;


import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {



    public static void main(String[] args) {

        String postData = "{\n" +
                "  \"idBoard\": \"61da76dc3cbb7d08d85c2f17\",\n" +
                "  \"key\": \"4040a9a3e24652d77ecb67da3090c926\"\n" +
                "  \"token\": \"6dee4961adb09f5e85fde8d7629416857c9b14fe00d370f28cebdff87ddc4eea\",\n" +
                "  \"name\": \"frontend24\",\n" +
                "}";

        given()
                .contentType(JSON);
              //  .queryParam(postData);
        when()
                .post("https://api.trello.com/1/lists?idBoard=61da76dc3cbb7d08d85c2f17&key=4040a9a3e24652d77ecb67da3090c926&token=6dee4961adb09f5e85fde8d7629416857c9b14fe00d370f28cebdff87ddc4eea&name=frontend23").
                then()
                .statusCode(200)
                .body("name",equalTo("frontend24"))
                .body("closed",equalTo("false"));

    }
}
