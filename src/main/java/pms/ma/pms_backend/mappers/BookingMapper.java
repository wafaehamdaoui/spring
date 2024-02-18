package pms.ma.pms_backend.mappers;

import org.springframework.stereotype.Component;
import pms.ma.pms_backend.dto.BookingDTO;
import pms.ma.pms_backend.dto.HotelDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.Booking;
import pms.ma.pms_backend.entities.Room;

@Component
public class BookingMapper {


    public static BookingDTO fromBookingTOBookingDTO(Booking booking) {
        BookingDTO bookingDto = new BookingDTO();
        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setRoomId(booking.getRoomId());
        bookingDto.setCheckInDate(booking.getCheckInDate());
        bookingDto.setCheckOutDate(booking.getCheckOutDate());
        bookingDto.setGuestName(booking.getGuestName());
        bookingDto.setGuestEmail(booking.getGuestEmail());
        bookingDto.setTotalPrice(booking.getTotalPrice());
        return bookingDto;
    }

    public static Booking fromBookingDTOtoBooking(BookingDTO bookingDto) {
        Booking booking = new Booking();
        booking.setBookingId(bookingDto.getBookingId());
        booking.setRoomId(bookingDto.getRoomId());
        booking.setCheckInDate(bookingDto.getCheckInDate());
        booking.setCheckOutDate(bookingDto.getCheckOutDate());
        booking.setGuestName(bookingDto.getGuestName());
        booking.setGuestEmail(bookingDto.getGuestEmail());
        booking.setTotalPrice(bookingDto.getTotalPrice());
        return booking;
    }

}
