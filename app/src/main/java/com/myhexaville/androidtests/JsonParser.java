package com.myhexaville.androidtests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class JsonParser {

    private static Gson gson = new Gson();

    public static Location parseLocationFromJson(String json) {
        if (json == null || json.isEmpty()) {
            return new Location();
        }

        try {
            JsonObject input = gson.fromJson(json, JsonObject.class);
            String cityName = null;
            double lat = 0.0;
            double lng = 0.0;

            JsonArray results = input.getAsJsonArray("results");
            if (results != null && results.size() > 0) {
                JsonObject firstItem = results.get(0).getAsJsonObject();
                if (firstItem.has("formatted_address")) {
                    cityName = firstItem.get("formatted_address").getAsString();
                }
                if (firstItem.has("geometry")) {
                    JsonObject geometry = firstItem.get("geometry").getAsJsonObject();
                    if (geometry.has("location")) {
                        JsonObject location = geometry.get("location").getAsJsonObject();
                        if (location.has("lat")) {
                            lat = location.get("lat").getAsDouble();
                        }
                        if (location.has("lng")) {
                            lng = location.get("lng").getAsDouble();
                        }
                    }
                }

                return new Location(cityName, lat, lng);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return new Location();
    }
}
