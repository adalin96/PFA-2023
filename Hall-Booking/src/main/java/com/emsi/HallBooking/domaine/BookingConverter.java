package com.emsi.HallBooking.domaine;

import com.emsi.HallBooking.service.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingConverter {

    //fonction qui fait la convertion du Booking en BookingVo
    public static BookingVo toBookingVo(Booking booking) {
        if (booking == null || booking.getIdBooking() == null) {
            return null;
        }

        BookingVo bookingVo = new BookingVo();
        bookingVo.setIdBooking(booking.getIdBooking());
        bookingVo.setDate(booking.getDate());
        bookingVo.setIdHall(booking.getIdHall());
        bookingVo.setIdClient(booking.getIdClient());

        return bookingVo;
    }

    //fonction qui fait la convertion du BookingVo en Booking
    public static Booking toBooking(BookingVo bookingVo) {
        if (bookingVo == null || bookingVo.getIdBooking() == null) {
            return null;
        }

        Booking booking = new Booking();

        booking.setIdBooking(bookingVo.getIdBooking());
        booking.setDate(bookingVo.getDate());
        booking.setIdHall(bookingVo.getIdHall());
        booking.setIdClient(bookingVo.getIdClient());


        return booking;
    }

    //fonction convertie une list de Booking en une list de BookingVo
    public static List<BookingVo> toListBookingVo(List<Booking> listBooking) {
        List<BookingVo> listBookingVo = new ArrayList<>();
        for (Booking booking : listBooking) {
            listBookingVo.add(toBookingVo(booking));
        }
        return listBookingVo;
    }

}
