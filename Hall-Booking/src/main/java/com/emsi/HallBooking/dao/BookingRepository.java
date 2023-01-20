package com.emsi.HallBooking.dao;


import com.emsi.HallBooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository  extends JpaRepository<Booking, Long> {

    List<Booking> findByDate(Date date);
    List<Booking> findByIdHall(Long idHall);
    List<Booking> findByIdClient(Long IdClient);
}
