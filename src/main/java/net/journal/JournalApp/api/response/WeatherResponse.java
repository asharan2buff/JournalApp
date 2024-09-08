package net.journal.JournalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//json to pojo is called deserialisation. reverse is serialisation
@Getter
@Setter
public class WeatherResponse {

    @JsonProperty("WeatherText")
    private String weatherText;

    @JsonProperty("Temperature")
    private Temperature temperature;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metric {
        @JsonProperty("Value")
        private double value;

        @JsonProperty("Unit")
        private String unit;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Temperature {
        @JsonProperty("Metric")
        private Metric metric;
    }
}
