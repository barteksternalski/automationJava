import environment.propertyLoader;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.*;
import org.awaitility.Awaitility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class SpinTest {

    private static propertyLoader propertyLoader;

    @Before
    public void setup() {
        propertyLoader = new propertyLoader("application.properties");
        Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, MILLISECONDS);
        Awaitility.setDefaultPollInterval(1, SECONDS);
        Awaitility.setDefaultTimeout(30, SECONDS);
        RestAssured.baseURI = propertyLoader.loadProperty("baseURL");
    }

    @Test
    public void userBetsAndSpin() {
        final Response[] response = {
                given()
//                    .log().all()
                    .param("fn", "authenticate")
                    .param("org", propertyLoader.loadProperty("organization"))
                    .param("gameid", propertyLoader.loadProperty("gameID"))
                .when().get("/service")
        };
        JsonPath json = response[0].jsonPath();
        Assert.assertEquals(0, (int)json.get("code"));
        String sessid = json.get("data.sessid");

        await().untilAsserted(() -> {
                    response[0] = given()
//                            .log().all()
                                .param("fn", "play")
                                .param("org", propertyLoader.loadProperty("organization"))
                                .param("gameid", propertyLoader.loadProperty("gameID"))
                                .param("sessid", sessid)
                                .param("currency", "EUR")
                                .param("coin", 0.05)
                                .param("amount", 1.25)
                            .when()
                                .get("/service");
                    Assert.assertEquals("Pending", response[0].jsonPath().get("data.wager.status"));
                });

        SpinResponsePending respPending = response[0].as(SpinResponsePending.class, ObjectMapperType.GSON);

        response[0] = given()
                .param("fn", "play")
                .param("org", propertyLoader.loadProperty("organization"))
                .param("gameid", propertyLoader.loadProperty("gameID"))
                .param("sessid", sessid)
                .param("currency", "EUR")
                .param("coin", 0.05)
                .param("amount", 0)
                .param("wagerid", respPending.getData().getWager().getWagerid())
                .param("betid", 1)
                .param("step", 2)
                .param("cmd", respPending.getData().getWager().getBets().get(respPending.getData().getWager().getBets().size() - 1).getEventdata().getNextCmds())
            .when()
                .get("/service");

        SpinResponseFinished respFinished = response[0].as(SpinResponseFinished.class, ObjectMapperType.GSON);

        System.out.println(respFinished.getData().getWager().getBets().get(0).getWonamount());
    }
}
