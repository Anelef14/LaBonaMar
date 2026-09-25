package com.backend.LaBonaMar.service;

import com.backend.LaBonaMar.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    public ReservationDto createReservation (ReservationDto reservationDto);
    public List<ReservationDto> getAllAvailableReservations();
}
