package pms.ma.pms_backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pms.ma.pms_backend.dto.BookingDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.Room;

import java.util.List;

public interface RoomService {
    List<RoomDTO> getAllRooms();
    public Page<RoomDTO> getAllRoomsAndPagenation(Pageable pageable);
    RoomDTO getRoomById(Long room_id);
    RoomDTO addRoom(RoomDTO roomDTO);
    RoomDTO updateRoom(RoomDTO roomDTO);
    void deleteRoom(Long id);
    List<RoomDTO> getRoomByNumber(int num);
    List<RoomDTO> getAvailableRooms();

    List<BookingDTO> getBookingsRoom(Long id);


    Page<RoomDTO> getRoomByTypeAndPagenation(Pageable pageable,String key);

    List<RoomDTO> getRoomByTypeAndHotel(String type, Long hotelId);


    Page<RoomDTO> getRoomByAttributteAndPagenation(Pageable pageable,String key, String value);

}
