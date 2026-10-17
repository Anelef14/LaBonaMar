package com.backend.LaBonaMar.service;

import com.backend.LaBonaMar.dto.RescheduleRequestDto;
import com.backend.LaBonaMar.dto.ReservationDto;
import com.backend.LaBonaMar.dto.ReservationResponseDto;

import java.util.List;

public interface ReservationService {
    public ReservationDto createReservation (ReservationDto reservationDto);
    public List<ReservationDto> getAllAvailableReservations();
    ReservationResponseDto rescheduleReservation (String Token, RescheduleRequestDto rescheduleRequestDto);
}
