package Steps;

import cucumber.api.java.sl.In;
import cucumber.api.java.tr.Diyelimki;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.json.Json;

import java.io.File;
import java.io.InputStream;
import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class trello {
    private static String keyTrello="4040a9a3e24652d77ecb67da3090c926";
    private static String tokenTrello="6dee4961adb09f5e85fde8d7629416857c9b14fe00d370f28cebdff87ddc4eea";


    public static void creatingNewboard(String name){
        RestAssured.baseURI="https://api.trello.com/1/boards/?key="+keyTrello+"&token="+tokenTrello+"&name="+name+"";
        requestSpecification = RestAssured.given();
        requestSpecification.
                contentType(JSON);
        requestSpecification.
                when().post(baseURI).then().statusCode(HttpStatus.SC_OK)
                .body("name",equalTo(name))
                .body("prefs.background",equalTo("blue"));

    }

    public static void main (String[] args){
        RestAssured.baseURI="https://api.trello.com/1/lists?idBoard=&key=4040a9a3e24652d77ecb67da3090c926&token=6dee4961adb09f5e85fde8d7629416857c9b14fe00d370f28cebdff87ddc4eea&name=frontend23";
        requestSpecification = RestAssured.given();
        requestSpecification.contentType(TEXT);
        requestSpecification.
                when().post(baseURI).then().statusCode(HttpStatus.SC_OK)
                .body("name",equalTo("fronted23"));





    }


    public void createUserTest() {
        RestAssured.baseURI = "https://api.trello.com/1/lists?idBoard=61da76dc3cbb7d08d85c2f17&key=4040a9a3e24652d77ecb67da3090c926&token=6dee4961adb09f5e85fde8d7629416857c9b14fe00d370f28cebdff87ddc4eea&name=frontend24";
         Response response = given().when().post(baseURI);

    }








    @Diyelimki("trello uzerinde {string} isimli bir board olusturalim")
    public void trelloUzerindeIsimliBirBoardOlusturalim(String name) {
        creatingNewboard(name);
        createUserTest();
    }
}
