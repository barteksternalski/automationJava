package environment;

public class EnvironmentFactory {

    private static String BROWSER;

    public static boolean isLocal() {
        String IS_LOCAL = System.getenv(EnvironmentConstant.IS_LOCAL) != null ? System.getenv(EnvironmentConstant.IS_LOCAL) : System.getProperty(EnvironmentConstant.IS_LOCAL);
        return IS_LOCAL != null && IS_LOCAL.toUpperCase().equals("TRUE");
    }

    public static boolean isRemote() {
        String IS_REMOTE = System.getenv(EnvironmentConstant.IS_REMOTE) != null ? System.getenv(EnvironmentConstant.IS_REMOTE) : System.getProperty(EnvironmentConstant.IS_REMOTE);
        return IS_REMOTE != null && IS_REMOTE.toUpperCase().equals("TRUE");
    }

    public static boolean isChrome() {
        BROWSER = System.getenv(EnvironmentConstant.BROWSER) != null ? System.getenv(EnvironmentConstant.BROWSER) : System.getProperty(EnvironmentConstant.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("CHROME");
    }

    public static boolean isFirefox() {
        BROWSER = System.getenv(EnvironmentConstant.BROWSER) != null ? System.getenv(EnvironmentConstant.BROWSER) : System.getProperty(EnvironmentConstant.BROWSER);
        return BROWSER != null && BROWSER.toUpperCase().equals("FIREFOX");
    }

}
