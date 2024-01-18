package com.chiratsxki.spaceconsole.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;


public class Asteroids {

    public static String processAsteroidsCommand() {

        String apiUrl = "https://api.nasa.gov/neo/rest/v1/feed?api_key=pFpuICnodTfsxftpbHm9Y3cz9xeeBFCwRzkHBRYB";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();



                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return getFirstAsteroidInfo(response.toString());
            } else {
                return "HTTP request failed with status code: " + connection.getResponseCode();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }

    }

    private static String getFirstAsteroidInfo(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        JsonNode nearEarthObjects = rootNode.path("near_earth_objects");
        Iterator<Map.Entry<String, JsonNode>> fields = nearEarthObjects.fields();

        if (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            JsonNode firstAsteroid = entry.getValue().get(0);

            return formatJson(firstAsteroid.toString());
        } else {
            return "No asteroid information available.";
        }
    }

    private static String formatJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(json);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);
    }

}

