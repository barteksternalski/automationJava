package environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class propertyReader {

    private String propertyFileName;

    public propertyReader(String propertyPath) {
        this.propertyFileName = propertyPath;
    }

    public String loadProperty(String name) {
        Properties props = new Properties();
        try {

            props.load(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + propertyFileName), "utf-8")));
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