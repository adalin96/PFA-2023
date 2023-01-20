package com.emsi.HallBooking.service;

import com.emsi.HallBooking.domaine.BookingVo;
import java.util.Date;
import java.util.List;

public interface IBookingService {

    List<BookingVo> getBookings();

    void save(BookingVo bookingVo);

    BookingVo getBookingById(Long idBooking);

    void delete(Long idBooking);

    List<BookingVo> findByDate(Date date);

    List<BookingVo> findByIdHall(Long idHall);

    List<BookingVo> findByIdClient(Long idClient);

    //multiple variable search
    public List<BookingVo> isAvailable(Date date, Long idHall, Long idClient);

    //Pour la pagination
    List<BookingVo> findAll(int pageId, int size);

    //pour le tri
    List<BookingVo> sortBy(String fieldName);
}
