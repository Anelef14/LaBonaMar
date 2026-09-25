package com.backend.LaBonaMar.mapper;

import com.backend.LaBonaMar.dto.ReservationDto;
import com.backend.LaBonaMar.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationDto dto) {

        Reservation reservation = new Reservation();

        reservation.setNumOfPerson(dto.getNumOfPerson());
        reservation.setName(dto.getName());
        reservation.setDate(dto.getDate());
        reservation.setTime(dto.getTime());
        reservation.setReserved(true);

        return reservation;
    }

    public ReservationDto toDto(Reservation reservation) {

        ReservationDto dto = new ReservationDto();

        dto.setNumOfPerson(reservation.getNumOfPerson());
        dto.setName(reservation.getName());
        dto.setDate(reservation.getDate());
        dto.setTime(reservation.getTime());

        return dto;
}}