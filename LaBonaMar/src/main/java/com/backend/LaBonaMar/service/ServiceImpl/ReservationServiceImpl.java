package com.backend.LaBonaMar.service.ServiceImpl;

import com.backend.LaBonaMar.dto.ReservationDto;
import com.backend.LaBonaMar.entity.Reservation;
import com.backend.LaBonaMar.mapper.ReservationMapper;
import com.backend.LaBonaMar.repository.ReservationRepository;
import com.backend.LaBonaMar.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    protected ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public ReservationDto createReservation (ReservationDto reservationDto){
    boolean output = reservationRepository.existsByDateAndTime(reservationDto.getDate(),reservationDto.getTime());
    if (output){
        throw new RuntimeException("This time slot is already reserved");
    }
    Reservation entity = reservationMapper.toEntity(reservationDto);
        Reservation save = reservationRepository.save(entity);
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

}
