package com.emsi.HallBooking.service;

import com.emsi.HallBooking.dao.BookingRepository;
import com.emsi.HallBooking.domaine.BookingConverter;
import com.emsi.HallBooking.domaine.BookingVo;
import com.emsi.HallBooking.service.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements IBookingService{

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<BookingVo> getBookings() {
        List<Booking> list = bookingRepository.findAll();
        return BookingConverter.toListBookingVo(list);
    }

    @Override
    public void save(BookingVo bookingVo) {
        bookingRepository.save(BookingConverter.toBooking(bookingVo));

    }

    @Override
    public BookingVo getBookingById(Long idBooking) {
        boolean found = bookingRepository.existsById(idBooking);
        if (!found)
            return null;
        return BookingConverter.toBookingVo(bookingRepository.getOne(idBooking));
    }

    @Override
    public void delete(Long idBooking) {
        bookingRepository.deleteById(idBooking);
    }

    @Override
    public List<BookingVo> findByDate(Date date) {
        List<Booking> list = bookingRepository.findByDate(date);
        return BookingConverter.toListBookingVo(list);
    }

    @Override
    public List<BookingVo> findByIdHall(Long idHall) {
        List<Booking> list = bookingRepository.findByIdHall(idHall);
        return BookingConverter.toListBookingVo(list);
    }

    @Override
    public List<BookingVo> findByIdClient(Long idClient) {
        List<Booking> list = bookingRepository.findByIdClient(idClient);
        return BookingConverter.toListBookingVo(list);
    }

    @Override
    public List<BookingVo> search(Date date, Long idHall, Long idClient) {
        // Only look for non-null attributes using Query By Example that has only non-null attributes
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();

        Booking booking = new Booking();

        booking.setDate(date);
        booking.setIdHall(idHall);
        booking.setIdClient(idClient);


        Example<Booking> query = Example.of(booking, matcher);
        return BookingConverter.toListBookingVo(bookingRepository.findAll(query, Sort.by("idBooking")));
    }

    @Override
    public List<BookingVo> findAll(int pageId, int size) {
        Page<Booking> result = bookingRepository.findAll(PageRequest.of(pageId, size, Sort.Direction.ASC, "name"));
        return BookingConverter.toListBookingVo(result.getContent());
    }

    @Override
    public List<BookingVo> sortBy(String fieldName) {
        return BookingConverter.toListBookingVo(bookingRepository.findAll(Sort.by(fieldName)));
    }
}
