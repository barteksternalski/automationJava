import api.gameRequests;
import environment.propertyLoader;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.*;
import org.awaitility.Awaitility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void userBetsAndSpin() {
        final Response[] response = {gameRequests.getAuthenticate(
                propertyLoader.loadProperty("baseURL"),
                propertyLoader.loadProperty("organization"),
                propertyLoader.loadProperty("gameID")
        )};
        Assert.assertEquals(0, (int) gameRequests.getDataFromResponse(response[0], "code"));
        String sessid = (String) gameRequests.getDataFromResponse(response[0], "data.sessid");

        await().untilAsserted(() -> {
                    response[0] = gameRequests.getPlayPending(
                            propertyLoader.loadProperty("baseURL"),
                            propertyLoader.loadProperty("organization"),
                            propertyLoader.loadProperty("gameID"),
                            sessid,
                            "EUR",
                            0.05,
                            1.25
                    );
                    Assert.assertEquals("Pending", gameRequests.getDataFromResponse(response[0], "data.wager.status"));
                });

        SpinResponsePending respPending = response[0].as(SpinResponsePending.class, ObjectMapperType.GSON);

        response[0] = gameRequests.getPlayFinished(
                propertyLoader.loadProperty("baseURL"),
                propertyLoader.loadProperty("organization"),
                propertyLoader.loadProperty("gameID"),
                sessid,
                "EUR",
                0.05,
                0,
                respPending.getData().getWager().getWagerid(),
                1,
                2,
                respPending.getData().getWager().getBets().get(respPending.getData().getWager().getBets().size() - 1).getEventdata().getNextCmds()
        );

        SpinResponseFinished respFinished = response[0].as(SpinResponseFinished.class, ObjectMapperType.GSON);

        System.out.println("User has won: " + respFinished.getData().getWager().getBets().get(0).getWonamount());
    }
}
