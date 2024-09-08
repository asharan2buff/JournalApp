package net.journal.JournalApp.service;

import net.journal.JournalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WeatherService {
    private static final String apiKey = "1ATJysW90wYgLLWJaRXoOBowUAAvD67Q";

    private static final String locationKey = "2146519";

    private static final String API = "http://dataservice.accuweather.com/currentconditions/v1/{locationKey}/historical/24?apikey={apiKey}";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getFirstWeatherInfo() {
        String finalAPI = API.replace("{locationKey}", locationKey).replace("{apiKey}", apiKey);

        ResponseEntity<List<WeatherResponse>> response = restTemplate.exchange(
                finalAPI,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<WeatherResponse>>() {}
        );

        List<WeatherResponse> weatherList = response.getBody();

        if (weatherList != null && !weatherList.isEmpty()) {
            // Return the first weather info from the list
            return weatherList.get(0);
        }
        return null;
    }
}
