package trueaccord.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class EndpointConsumer {
    
    private static final boolean DEBUG = false;
    
    public static String getJsonString(String urlEndpoint) throws Exception {
        URL urlObj = new URL(urlEndpoint);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        debug("Send 'HTTP GET' request to : " + urlEndpoint);

        Integer responseCode = connection.getResponseCode();
        debug("Response Code : " + responseCode);

        String jsonData = "";
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = inputReader.readLine()) != null) {
                response.append(inputLine);
            }

            inputReader.close();

            debug(response.toString());
            jsonData = response.toString();
        }
        
        return jsonData;
    }
    
    public static JSONArray getJsonArray(String urlEndpoint) throws Exception {
        return (JSONArray)JSONValue.parse( getJsonString(urlEndpoint) );
    }
    
    private static void debug(String msg) {
        if (DEBUG)
            System.out.println(msg);
    }
    
}
