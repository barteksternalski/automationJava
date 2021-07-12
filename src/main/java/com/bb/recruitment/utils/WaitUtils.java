package com.bb.recruitment.utils;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.WebDriverRunner;

public final class WaitUtils {

    public static boolean waitForConditionToBeFulfilled(Callable<Boolean> condition) {
        return waitForConditionToBeFulfilled(condition, 10000L);
    }

    public static boolean waitForConditionToBeFulfilled(Callable<Boolean> condition, long timeoutInMillis) {
        WebDriver webDriver = WebDriverRunner.getWebDriver();
        AtomicReference<Boolean> conditionResult = new AtomicReference<>();
        try {
            await()
                    .atMost(timeoutInMillis, MILLISECONDS)
                    .ignoreExceptions()
                    .pollInterval(10, MILLISECONDS)
                    .until(() -> {
                        WebDriverRunner.setWebDriver(webDriver);
                        conditionResult.set(condition.call());
                        return conditionResult.get();
                    });
        } catch (ConditionTimeoutException e) {
            return false;
        }
        return conditionResult.get();
    }

}
