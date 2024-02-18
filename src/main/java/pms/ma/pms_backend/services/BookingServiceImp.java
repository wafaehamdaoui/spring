package pms.ma.pms_backend.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pms.ma.pms_backend.dto.BookingDTO;
import pms.ma.pms_backend.dto.HotelDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.Booking;
import pms.ma.pms_backend.entities.Hotel;
import pms.ma.pms_backend.entities.Room;
import pms.ma.pms_backend.mappers.BookingMapper;
import pms.ma.pms_backend.repositories.BookingRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImp implements BookingService{

    private final  BookingRepository bookingRepository;

    public BookingServiceImp(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    @Override
    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        Booking booking=bookingRepository.save(BookingMapper.fromBookingDTOtoBooking(bookingDTO));


        return  BookingMapper.fromBookingTOBookingDTO(booking);
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Page<BookingDTO> getBookingByGuestNameAndPagenation(Pageable pageable, String keyword) {
        Page<Booking> bookings = bookingRepository.findByGuestName(keyword, pageable);
        List<BookingDTO> bookingDTOList = bookings.getContent().stream()
                .map(BookingMapper::fromBookingTOBookingDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookingDTOList, pageable, bookings.getTotalElements());
    }


    @Override

    public BookingDTO getBookingById(Long booking_id) {
        Booking booking=this.bookingRepository.findById(booking_id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        System.out.println(booking.getRoomId());
        return BookingMapper.fromBookingTOBookingDTO(booking);
    }

    @Override
    public void deleteBooking(Long booking_id) {
        bookingRepository.deleteById(booking_id);
    }

    @Override
    public BookingDTO updateBooking(BookingDTO updatedBookingDTO) {
        BookingDTO existingBookingDTO = BookingMapper.fromBookingTOBookingDTO(bookingRepository.findById(updatedBookingDTO.getBookingId()).orElse(null));

        if (existingBookingDTO != null) {

            existingBookingDTO.setRoomId(updatedBookingDTO.getRoomId());
            existingBookingDTO.setCheckInDate(updatedBookingDTO.getCheckInDate());
            existingBookingDTO.setCheckOutDate(updatedBookingDTO.getCheckOutDate());
            existingBookingDTO.setGuestName(updatedBookingDTO.getGuestName());
            existingBookingDTO.setGuestEmail(updatedBookingDTO.getGuestEmail());
            existingBookingDTO.setTotalPrice(updatedBookingDTO.getTotalPrice());




        }
        Booking booking = this.bookingRepository.save(BookingMapper.fromBookingDTOtoBooking(existingBookingDTO));
        return BookingMapper.fromBookingTOBookingDTO(booking);

    }

    @Override
    public Page<BookingDTO> getAllBookingsAndPagenation(Pageable pageable) {
        Page<Booking> bookings = bookingRepository.findAll(pageable);
        List<BookingDTO> bookingDTOList = bookings.getContent().stream()
                .map(BookingMapper::fromBookingTOBookingDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookingDTOList, pageable, bookings.getTotalElements());
    }


}
