package helpers;

import java.util.HashMap;

public class DataHelper {

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    public DataHelper(HashMap<String, Object> map) {
        this.map = map;
    }

    public static HashMap<String, Object> putIntoMap(String key, Object value) {
        if (!value.equals("{skip}")) {
            map.put(key, value);
        }
        return map;
    }

}
