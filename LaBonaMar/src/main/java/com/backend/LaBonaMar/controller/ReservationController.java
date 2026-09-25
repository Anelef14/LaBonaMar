package com.backend.LaBonaMar.controller;

import com.backend.LaBonaMar.dto.ReservationDto;
import com.backend.LaBonaMar.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(
            @RequestBody ReservationDto reservationDto) {

        ReservationDto createdReservation =
                reservationService.createReservation(reservationDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdReservation);
    }


}