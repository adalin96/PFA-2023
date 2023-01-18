package com.emsi.HallBooking.controller.rest;

import com.emsi.HallBooking.domaine.BookingVo;
import com.emsi.HallBooking.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/rest/booking")
    public List<BookingVo> getAll() {
        return bookingService.getBookings();
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllBookings() {
        List<BookingVo> bookingVos = bookingService.getBookings();
        if (bookingService == null)
            return new ResponseEntity<>("the list of bookings is empty", HttpStatus.OK);
        return new ResponseEntity<>(bookingVos, HttpStatus.OK);
    }

    @GetMapping(value = "/rest/booking/{id}")
    public ResponseEntity<Object> getBookingById(@PathVariable(value = "id") Long bookingId) {
        BookingVo bookingVoFound = bookingService.getBookingById(bookingId);
        if (bookingVoFound == null)
            return new ResponseEntity<>("booking doesn't exist", HttpStatus.OK);
        return new ResponseEntity<>(bookingVoFound, HttpStatus.OK);
    }


    @GetMapping("/booking/search")
    public ResponseEntity<Object> searchBooking(@RequestParam("date") Date date, @RequestParam("idHall") Long idHall, @RequestParam("idClient") Long idClient) {
        List<BookingVo> bookingVo = bookingService.search(date, idHall, idClient);
        if (bookingVo == null)
            return new ResponseEntity<>("booking does not exit", HttpStatus.OK);
        return new ResponseEntity<>(bookingVo, HttpStatus.OK);
    }

    @PostMapping(value = "/rest/booking/create")
    public ResponseEntity<Object> createBooking(/*@Valid*/ @RequestBody BookingVo bookingVo) {
        bookingService.save(bookingVo);
        return new ResponseEntity<>("booking is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/rest/booking/update/{id}")
    public ResponseEntity<Object> updateBooking(@PathVariable(name = "id") Long bookingId, @RequestBody BookingVo bookingVo) {
        BookingVo bookingFound = bookingService.getBookingById(bookingId);
        if (bookingFound == null)
            return new ResponseEntity<>("Booking doesn't exist", HttpStatus.OK);
        bookingVo.setIdBooking(bookingId);
        bookingService.save(bookingVo);
        return new ResponseEntity<>("Booking is updated successsfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/rest/booking/delete/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable(name = "id") Long bookingId) {
        BookingVo bookingVoFound = bookingService.getBookingById(bookingId);
        if (bookingVoFound == null)
            return new ResponseEntity<>("Booking doesn't exist", HttpStatus.OK);
        bookingService.delete(bookingId);
        return new ResponseEntity<>("Booking is deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/rest/sort/{fieldName}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<BookingVo> sortBy(@PathVariable String fieldName) {
        return bookingService.sortBy(fieldName);
    }

    @GetMapping("/rest/pagination/{pageId}/{size}")
    public List<BookingVo> pagination(@PathVariable int pageId, @PathVariable int size, Model m) {
        return bookingService.findAll(pageId, size);
    }

}
