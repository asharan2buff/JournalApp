package net.journal.JournalApp.service;

import net.journal.JournalApp.api.response.WeatherResponse;
import net.journal.JournalApp.cache.AppCache;
import net.journal.JournalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component//can use @Service as well to denote it is a service class. Both do the same thing.i.e. create beans
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.location.key}")
    private String locationKey;//removed final

//    private static final String API = "http://dataservice.accuweather.com/currentconditions/v1/{locationKey}/historical/24?apikey={apiKey}";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getFirstWeatherInfo() {
        WeatherResponse weatherResponse = redisService.get("weather_of_amherst",WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }
        else
        {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.LOCATION_KEY, locationKey).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<List<WeatherResponse>> response = restTemplate.exchange(//execute HTTP requests and capture responses
                    finalAPI,
                    HttpMethod.GET,
                    null,//for post call we need to pass requestentity here. Play with 11labs api..
                    new ParameterizedTypeReference<List<WeatherResponse>>() {});
            List<WeatherResponse> weatherList = response.getBody();
            if (weatherList != null && !weatherList.isEmpty()) {
                redisService.set("weather_of_amherst",weatherList.get(0),300l);
                // Return the first weather info from the list
                return weatherList.get(0);
            }
        }
        return null;
    }
}
