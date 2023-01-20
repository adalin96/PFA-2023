package com.emsi.HallBooking.rest;

import com.emsi.HallBooking.domaine.BookingVo;
import com.emsi.HallBooking.service.IBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/booking")
public class BookingRestController {

    @Autowired
    private final IBookingService bookingService;

    public BookingRestController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllBookings() {
        List<BookingVo> bookingVos = bookingService.getBookings();
        if (bookingService == null)
            return new ResponseEntity<>("the list of bookings is empty", HttpStatus.OK);
        return new ResponseEntity<>(bookingVos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getBookingById(@PathVariable(value = "id") Long bookingVoId) {
        BookingVo bookingVoFound = bookingService.getBookingById(bookingVoId);
        if (bookingVoFound == null)
            return new ResponseEntity<>("booking doesn't exist", HttpStatus.OK);
        return new ResponseEntity<>(bookingVoFound, HttpStatus.OK);
    }


    @PostMapping("/search")
    public ResponseEntity<Object> searchBooking(@RequestBody BookingVo bookingVo) {
      List<BookingVo> bookingsVo = bookingService.isAvailable(bookingVo.getDate(), bookingVo.getIdHall(), bookingVo.getIdClient());
      if (bookingsVo == null)
        return new ResponseEntity<>("booking does not exit", HttpStatus.OK);
      return new ResponseEntity<>(bookingsVo, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> createBooking(@Valid @RequestBody BookingVo bookingVo) {
        bookingService.save(bookingVo);
        return new ResponseEntity<>("booking is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateBooking(@PathVariable(name = "id") Long bookingId, @RequestBody BookingVo bookingVo) {
        BookingVo bookingFound = bookingService.getBookingById(bookingId);
        if (bookingFound == null)
            return new ResponseEntity<>("Booking doesn't exist", HttpStatus.OK);
        bookingVo.setId(bookingId);
        bookingService.save(bookingVo);
        return new ResponseEntity<>("Booking is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable(name = "id") Long bookingId) {
        BookingVo bookingVoFound = bookingService.getBookingById(bookingId);
        if (bookingVoFound == null)
            return new ResponseEntity<>("Booking doesn't exist", HttpStatus.OK);
        bookingService.delete(bookingId);
        return new ResponseEntity<>("Booking is deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/sort/{fieldName}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<BookingVo> sortBy(@PathVariable String fieldName) {
        return bookingService.sortBy(fieldName);
    }

    @GetMapping("/page/{pageId}/{size}")
    public List<BookingVo> pagination(@PathVariable int pageId, @PathVariable int size, Model m) {
        return bookingService.findAll(pageId, size);
    }

}
