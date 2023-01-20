package com.emsi.HallBooking.domaine;

import com.emsi.HallBooking.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingConverter {

    //fonction qui fait la convertion du Booking en BookingVo
    public static BookingVo toBookingVo(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingVo bookingVo = new BookingVo();

        bookingVo.setId(booking.getId());
        bookingVo.setDate(booking.getDate());
        bookingVo.setIdHall(booking.getIdHall());
        bookingVo.setIdClient(booking.getIdClient());

        return bookingVo;
    }

    //fonction qui fait la convertion du BookingVo en Booking
    public static Booking toBooking(BookingVo bookingVo) {
        if (bookingVo == null) {
            return null;
        }

        Booking booking = new Booking();

        booking.setId(bookingVo.getId());
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
