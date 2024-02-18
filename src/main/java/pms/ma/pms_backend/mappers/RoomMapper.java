package pms.ma.pms_backend.mappers;

import org.springframework.stereotype.Component;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.Room;

@Component
public class RoomMapper {

    public RoomDTO fromRoomToRoomDTO(Room room) {
        return RoomDTO.builder()
                .roomId(room.getRoomId())
                .roomNumber(room.getRoomNumber())
                .floorNumber(room.getFloorNumber())
                .type(room.getType())
                .hotelId(room.getHotelId())
                .pricePerNight(room.getPricePerNight())
                .available(room.isAvailable())
                .build();
    }

    public Room fromRoomDTOToRoom(RoomDTO roomDTO) {
        return Room.builder()
                .roomId(roomDTO.getRoomId())
                .roomNumber(roomDTO.getRoomNumber())
                .floorNumber(roomDTO.getFloorNumber())
                .hotelId(roomDTO.getHotelId())
                .type(roomDTO.getType())
                .pricePerNight(roomDTO.getPricePerNight())
                .available(roomDTO.isAvailable())
                .build();
    }

}
