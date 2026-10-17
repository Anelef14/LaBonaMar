package com.backend.LaBonaMar.service.ServiceImpl;

import com.backend.LaBonaMar.dto.RescheduleRequestDto;
import com.backend.LaBonaMar.dto.ReservationDto;
import com.backend.LaBonaMar.dto.ReservationResponseDto;
import com.backend.LaBonaMar.entity.Reservation;
import com.backend.LaBonaMar.entity.ReservationStatus;
import com.backend.LaBonaMar.mapper.ReservationMapper;
import com.backend.LaBonaMar.repository.ReservationRepository;
import com.backend.LaBonaMar.service.EmailService;
import com.backend.LaBonaMar.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final EmailService emailService;
    protected ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.emailService = emailService;
    }

    public ReservationDto createReservation (ReservationDto reservationDto){
    boolean output = reservationRepository.existsByDateAndTime(reservationDto.getDate(),reservationDto.getTime(), ReservationStatus.CONFIRMED);
    if (output){
        throw new RuntimeException("This time slot is already reserved");
    }
    Reservation entity = reservationMapper.toEntity(reservationDto);
    entity.setStatus(ReservationStatus.CONFIRMED);
    entity.setManagementToken(UUID.randomUUID().toString());
        Reservation save = reservationRepository.save(entity);
        String managementUrl = "http://localhost:5500/manage-reservation.html?token=" + save.getManagementToken();
        emailService.sendConfirmationEmail(save,managementUrl);
        return reservationMapper.toDto(save);
    }
    public List<ReservationDto> getAllAvailableReservations(){
        List<Reservation> reservations = reservationRepository.findByReservedFalse();
        List<ReservationDto> reservationDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationDtos.add(reservationMapper.toDto(reservation));
        }

        return reservationDtos;
    }

    @Override
    public ReservationResponseDto rescheduleReservation(String token, RescheduleRequestDto rescheduleRequestDto) {
        Reservation reservation = reservationRepository.findByManagementToken(token);
        if(reservation.getStatus()==ReservationStatus.CANCELLED){
            throw new RuntimeException("Reservation is already cancelled, make a new reservation");
        }
        reservation.setDate(rescheduleRequestDto.getDate());
        reservation.setTime(rescheduleRequestDto.getTime());
        Reservation saved = reservationRepository.save(reservation);
        String managementUrl = "http://localhost:5500/manage-reservation.html?token=" + saved.getManagementToken();
        emailService.sendUpdatedEmail(reservation, managementUrl);

        return reservationMapper.toResponseDto(saved);
    }


}
