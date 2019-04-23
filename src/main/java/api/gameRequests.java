package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class gameRequests {

    public static Object getDataFromResponse(Response response, String locator) {
        JsonPath json = response.jsonPath();
        return json.get(locator);
    }

    public static Response getAuthenticate(String baseURL,
                                           String organization,
                                           String gameID) {

        RestAssured.baseURI = baseURL;
        return given()
                .param("fn", "authenticate")
                .param("org", organization)
                .param("gameid", gameID)
            .when().get("/service");
    }

    public static Response getPlayPending(String baseURL,
                                          String organization,
                                          String gameID,
                                          String sessionID,
                                          String currency,
                                          double coin,
                                          double amount ) {

        RestAssured.baseURI = baseURL;
        return given()
                .param("fn", "play")
                .param("org", organization)
                .param("gameid", gameID)
                .param("sessid", sessionID)
                .param("currency", currency)
                .param("coin", coin)
                .param("amount", amount)
            .when()
                .get("/service");
    }

    public static Response getPlayFinished(String baseURL,
                                           String organization,
                                           String gameID,
                                           String sessionID,
                                           String currency,
                                           double coin,
                                           double amount,
                                           String wagerID,
                                           int betID,
                                           int step,
                                           String command) {
        RestAssured.baseURI = baseURL;
        return given()
                .param("fn", "play")
                .param("org", organization)
                .param("gameid", gameID)
                .param("sessid", sessionID)
                .param("currency", currency)
                .param("coin", coin)
                .param("amount", amount)
                .param("wagerid", wagerID)
                .param("betid", betID)
                .param("step", step)
                .param("cmd", command)
            .when()
                .get("/service");
    }


}
