package Steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import static io.restassured.RestAssured.*;

public class BaseRest {

    private String token="6dee4961adb09f5e85fde8d7629416857c9b14fe00d370f28cebdff87ddc4eea";
    private String key="4040a9a3e24652d77ecb67da3090c926";
    private String baseUrlLink="https://api.trello.com";
    private String boardName="Automation Trello celal";
    private String cardNameOne="Fronted";
    private String cardNameTwo="Backend";
    private String boardIdCreate;

    public String getBoardIdCreate() {
        return boardIdCreate;
    }

    public void setBoardIdCreate(String boardIdCreate) {
        this.boardIdCreate = boardIdCreate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBaseUrlLink() {
        return baseUrlLink;
    }

    public void setBaseUrlLink(String baseUrlLink) {
        this.baseUrlLink = baseUrlLink;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getCardNameOne() {
        return cardNameOne;
    }

    public void setCardNameOne(String cardNameOne) {
        this.cardNameOne = cardNameOne;
    }

    public String getCardNameTwo() {
        return cardNameTwo;
    }

    public void setCardNameTwo(String cardNameTwo) {
        this.cardNameTwo = cardNameTwo;
    }

    public void jsonParseMethod(Object objectName){

    }

   public static Object createBoard(String baseUrlLink,String key,String token,String name){
       BaseRest b =new BaseRest();
       b.setBaseUrlLink(baseUrlLink);
       b.setKey(key);
       b.setToken(token);
       b.setBoardName(name);
       RestAssured.baseURI=baseUrlLink;
       useRelaxedHTTPSValidation();
       RequestSpecification httpRequest=given().header("Content-Type","application/json").when();
       Response response =httpRequest.post("/1/boards/?key="+key+"&token="+token+"&name="+name+"&id=q7UMItOV");
       httpRequest.then().statusCode(200);
       String body=response.prettyPrint().toString();
       Object objectbody=Configuration.defaultConfiguration().jsonProvider().parse(body);
       Object objectboardId=JsonPath.read(objectbody,"$.id").toString();
       System.out.println(objectboardId);
      b.setBoardIdCreate(objectboardId.toString());
       return body;



    }

    public static void main(String[] argv) {
        BaseRest b =new BaseRest();

        createBoard(b.baseUrlLink,b.key,b.token,b.boardName);
    }


}
