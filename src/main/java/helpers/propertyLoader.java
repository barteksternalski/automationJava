package helpers;

import java.io.IOException;
import java.util.Properties;

public class propertyLoader {

    private String propertyFileName;

    public propertyLoader(String propertyPath) {
        this.propertyFileName = propertyPath;
    }

    public String loadProperty(String name) {
        Properties props = new Properties();
        try {

            props.load(propertyLoader.class.getResourceAsStream("/" + propertyFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

}
