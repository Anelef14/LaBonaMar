package com.backend.LaBonaMar.repository;

import com.backend.LaBonaMar.entity.Reservation;
import com.backend.LaBonaMar.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsByDateAndTime(LocalDate date, LocalTime time, ReservationStatus status);
    List<Reservation> findByReservedFalse();
    Reservation findByManagementToken(String managementToken);
    List<Reservation>findByDateAndStatus(LocalDate date, ReservationStatus status);
}
