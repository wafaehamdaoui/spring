package pms.ma.pms_backend.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pms.ma.pms_backend.dto.BookingDTO;
import pms.ma.pms_backend.dto.HotelDTO;
import pms.ma.pms_backend.entities.Booking;
import pms.ma.pms_backend.entities.User;

import java.util.Date;
import java.util.List;


public interface BookingService {

    BookingDTO saveBooking(BookingDTO bookingDTO);
    List<Booking> getAllBooking();

    Page<BookingDTO> getBookingByGuestNameAndPagenation(Pageable pageable,String name);

    BookingDTO getBookingById(Long booking_id);
    void deleteBooking(Long booking_id);
    BookingDTO updateBooking(BookingDTO updatedBookingDTO);


    Page<BookingDTO> getAllBookingsAndPagenation(Pageable pageable);
}
