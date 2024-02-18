package pms.ma.pms_backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pms.ma.pms_backend.entities.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findRoomByRoomNumber(int num, Pageable pageable);
    List<Room> findRoomByRoomNumber(int num);
    Page<Room> findRoomByFloorNumber(int num, Pageable pageable);
    List<Room> findRoomByFloorNumber(int num);
    List<Room> findRoomByHotelId(Long num);

    Page<Room> findRoomByAvailable(boolean status,Pageable pageable);
    List<Room> findRoomByAvailable(boolean status);



    List<Room> findRoomByTypeAndHotelIdAndAvailable(String type, Long hotelId,boolean available);


    Page<Room> findRoomByPricePerNight(int price,Pageable pageable);
    Page<Room> findRoomByType(String keyword, Pageable pageable);

}
