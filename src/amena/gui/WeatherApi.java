/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.gui;


import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;

import java.io.IOException;
import java.io.InputStream;


import org.json.JSONException;
import org.json.JSONObject;

public class WeatherApi extends Form {

    public WeatherApi(Form previous) {
        // Set the layout manager for the form
        super(new BorderLayout());

        // Set the city and country code for the API request
        String city = "Tunis";
        String countryCode = "tn";
        String apiKey = "b5f368112adba08ef3e1342643035eed";

        // Create the API endpoint URL
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + countryCode + "&appid=" + apiKey;

        // Create a connection request to the API endpoint
        ConnectionRequest request = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                try {
                    // Parse the JSON response from the API
                    String responseString = "";
                    int n;
                    byte[] buffer = new byte[1024];
                    while ((n = input.read(buffer)) != -1) {
                        responseString += new String(buffer, 0, n);
                    }
                    JSONObject response = new JSONObject(responseString);
double temperature = response.getJSONObject("main").getDouble("temp") - 273.15;
                    String description = response.getJSONArray("weather").getJSONObject(0).getString("description");

                    // Create labels to display the temperature and weather description
                    Label tempLabel = new Label(Double.toString(temperature));
                    Label descLabel = new Label(description);

                    // Add the labels to the form
                    add(BorderLayout.NORTH, new SpanLabel("Temperature:"));
                    add(BorderLayout.WEST, tempLabel);
                    add(BorderLayout.SOUTH, new SpanLabel("Description:"));
                    add(BorderLayout.EAST, descLabel);

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        };
        request.setUrl(apiUrl);
        NetworkManager.getInstance().addToQueueAndWait(request);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}