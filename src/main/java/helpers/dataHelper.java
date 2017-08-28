package helpers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class dataHelper {

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    public static HashMap<String, Object> putIntoMap(String key, Object value) {
        if (!value.equals("{skip}")) {
            map.put(key, value);
        }

        return map;
    }

    public static void clearMap(HashMap<String, Object> map) {
        if (map.size() > 0) map.clear();
    }

    public static List<Object> getListOfElements(JSONArray json, String sElement) {
        List<Object> temp = new ArrayList<Object>();
        for (int i =0; i < json.length(); i++) {
            JSONObject j = json.getJSONObject(i);
            temp.add(j.get(sElement));
        }
        return temp;
    }

    public static boolean verifyMessage(String response, String sNode, Object oCheck) {
        boolean temp;
        //System.out.println(response);
        JSONObject jobj = new JSONObject(response);
        if (jobj.get(sNode).equals(oCheck)) {
            temp = true;
        } else {
            temp = false;
        }
        return temp;
    }

    public static String generateDayAndHour() {
        Date date = new Date();
        return date.toString().substring(4, 7) + date.toString().substring(8, 10) + "_" + date.toString().substring(11, 13) + date.toString().substring(14, 16) + date.toString().substring(17, 19);
    }

    public static String generatePhone(String sLocale) {
        Random rand = new Random();
        String tempPhone = "";
        switch(sLocale.substring(0, 2)) {
            case "cn":
                tempPhone = Integer.toString(rand.nextInt(99999999));
                while(tempPhone.length() < 8) {
                    tempPhone = "0".concat(tempPhone);
                }
                tempPhone = "155".concat(tempPhone);
                break;
            case "hk":
                tempPhone = Integer.toString(rand.nextInt(999999999));
                while(tempPhone.length() < 9) {
                    tempPhone = "0".concat(tempPhone);
                }
                tempPhone = "5".concat(tempPhone);
                break;
            case "us":
            case "cl":
            case "ar":
            case "mx":
            case "br":
            case "ja":
            case "in":
            case "au":
                break;
        }
        return tempPhone;
    }

}
