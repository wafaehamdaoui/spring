package pms.ma.pms_backend.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pms.ma.pms_backend.dto.BookingDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.Room;
import pms.ma.pms_backend.exceptions.RoomNotFoundException;
import pms.ma.pms_backend.mappers.BookingMapper;
import pms.ma.pms_backend.mappers.RoomMapper;
import pms.ma.pms_backend.repositories.RoomRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomServiceImp implements RoomService{
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final BookingMapper bookingMapper;

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(roomMapper::fromRoomToRoomDTO)
                .collect(Collectors.toList());
    }
    public Page<RoomDTO> getAllRoomsAndPagenation(Pageable pageable) {
        Page<Room> rooms = roomRepository.findAll(pageable);
        List<RoomDTO> roomDTOList = rooms.getContent().stream()
                .map(roomMapper::fromRoomToRoomDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(roomDTOList, pageable, rooms.getTotalElements());
    }




    @Override
    public RoomDTO getRoomById(Long room_id) {
        Room room = roomRepository.findById(room_id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        return roomMapper.fromRoomToRoomDTO(room);
    }

    @Override
    public RoomDTO addRoom(RoomDTO roomDTO) {
        Room room = roomMapper.fromRoomDTOToRoom(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.fromRoomToRoomDTO(savedRoom);
    }

    @Override
    public RoomDTO updateRoom(RoomDTO roomDTO) {
        Room room = roomRepository.save(roomMapper.fromRoomDTOToRoom(roomDTO));
        return roomMapper.fromRoomToRoomDTO(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<RoomDTO> getRoomByNumber(int num) {
        List<Room> rooms=roomRepository.findRoomByRoomNumber(num);
        return rooms.stream().map(room -> roomMapper.fromRoomToRoomDTO(room)).collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getAvailableRooms() {
        List<Room> rooms=roomRepository.findRoomByAvailable(true);
        return rooms.stream().map(room -> roomMapper.fromRoomToRoomDTO(room)).collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsRoom(Long id) {
        Room room=roomRepository.findById(id).orElseThrow(
                ()->new RoomNotFoundException("no room founded")
        );
        List<BookingDTO> bookingDTOS = room.getBookings().stream().map(booking->bookingMapper.fromBookingTOBookingDTO(booking)).collect(Collectors.toList());
        return bookingDTOS;
    }

    @Override
    public Page<RoomDTO> getRoomByTypeAndPagenation(Pageable pageable, String key) {
        return null;
    }

    @Override
    public Page<RoomDTO> getRoomByAttributteAndPagenation(Pageable pageable,String keyword, String value) {
        Page<Room> rooms = null;
        if(Objects.equals(keyword, "type")){
            rooms=roomRepository.findRoomByType(value,pageable);
        } else if (Objects.equals(keyword, "floor")) {
            rooms=roomRepository.findRoomByFloorNumber(Integer.parseInt(value),pageable);

        }else if (Objects.equals(keyword, "price")) {
            rooms=roomRepository.findRoomByPricePerNight(Integer.parseInt(value),pageable);

        } else if (Objects.equals(keyword, "number")) {
            rooms=roomRepository.findRoomByRoomNumber(Integer.parseInt(value),pageable);
        } else if (Objects.equals(keyword, "status")) {
            boolean status = false;
            if (value.compareToIgnoreCase("Available")==0){
                status=true;
            }else if (value.compareToIgnoreCase("Occupied")==0){
                status=false;
            }
            rooms=roomRepository.findRoomByAvailable(status,pageable);
        }
        List<RoomDTO> roomDTOList = rooms.getContent().stream()
                .map(roomMapper::fromRoomToRoomDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(roomDTOList, pageable, rooms.getTotalElements());
    }



    @Override
    public List<RoomDTO> getRoomByTypeAndHotel(String type, Long hotelId) {
        List<Room> rooms = roomRepository.findRoomByTypeAndHotelIdAndAvailable(type, hotelId,true);


        List<RoomDTO> roomDTOS = rooms.stream()
                .map(room -> roomMapper.fromRoomToRoomDTO(room))
                .collect(Collectors.toList());
        return roomDTOS; // Renvoyer la liste des DTO RoomDTO
    }


}
