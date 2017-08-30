package helpers;

import environment.EnvironmentConstant;

import java.util.Map;

public class EnvironmentHelper {

    public static void setEnv(Map<String, String> newenv) {
        clearAllProperties();

        for (Map.Entry entry : newenv.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private static void clearAllProperties() {
        System.clearProperty(EnvironmentConstant.IS_LOCAL);
        System.clearProperty(EnvironmentConstant.IS_REMOTE);
        System.clearProperty(EnvironmentConstant.BROWSER);
    }

}
