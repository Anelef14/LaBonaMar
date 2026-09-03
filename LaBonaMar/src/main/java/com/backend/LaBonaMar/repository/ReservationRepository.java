package com.backend.LaBonaMar.repository;

import com.backend.LaBonaMar.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Integer, Reservation> {
}
