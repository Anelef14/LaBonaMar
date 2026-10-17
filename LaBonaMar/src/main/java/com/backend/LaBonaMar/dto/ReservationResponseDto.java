package com.backend.LaBonaMar.dto;

import com.backend.LaBonaMar.entity.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {

    private int id;
    private int numOfPerson;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String email;
    private ReservationStatus status;
    private String managementUrl;
}
