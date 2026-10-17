
package com.backend.LaBonaMar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ReservationConfig {

    private List<LocalTime> availableTimes;

    public ReservationConfig(
            @Value("${reservation.available-times:13:00,13:15,13:30,13:45,14:00,14:15,14:30,14:45,15:00,15:15,15:30,15:45,16:00,16:15,16:30,16:45}")
            String availableTimes) {

        this.availableTimes = Arrays.stream(availableTimes.split(","))
                .map(String::trim)
                .map(LocalTime::parse)
                .collect(Collectors.toList());
    }

    public List<LocalTime> getAvailableTimes() {
        return availableTimes;
    }
}
