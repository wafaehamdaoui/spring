package pms.ma.pms_backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pms.ma.pms_backend.dto.BookingDTO;
import pms.ma.pms_backend.dto.HotelDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.Booking;
import pms.ma.pms_backend.entities.User;
import pms.ma.pms_backend.mappers.BookingMapper;
import pms.ma.pms_backend.services.BookingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBooking();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/pages")


    Page<BookingDTO> getAllBookingPages(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int per_page,
                                      @RequestParam(defaultValue = "") String keyword,
                                      @RequestParam(defaultValue = "guestName") String sortBy) {
        Pageable pageable = PageRequest.of(page, per_page, Sort.by(sortBy));

        if (keyword != null && !keyword.isEmpty()) {
            return bookingService.getBookingByGuestNameAndPagenation(pageable,keyword);
        }else{
            return bookingService.getAllBookingsAndPagenation(pageable);
        }
    }
    @GetMapping("/{booking_id}")
    public BookingDTO getBookingById(@PathVariable Long booking_id) {
        return bookingService.getBookingById(booking_id);
    }
    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.saveBooking(bookingDTO);
    }

    @PutMapping("")
    public BookingDTO updateBooking(@RequestBody BookingDTO updatedBookingDTO) {

        return bookingService.updateBooking(updatedBookingDTO);
    }



    @DeleteMapping("/{booking_id}")
    public void  deleteBooking(@PathVariable Long booking_id) {
        bookingService.deleteBooking(booking_id);

    }




}
