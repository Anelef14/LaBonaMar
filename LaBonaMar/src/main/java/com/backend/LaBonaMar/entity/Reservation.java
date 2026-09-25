package com.backend.LaBonaMar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numOfPerson;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private boolean reserved;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private String managementToken;
}
