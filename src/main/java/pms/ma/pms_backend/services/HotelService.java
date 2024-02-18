package pms.ma.pms_backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pms.ma.pms_backend.dto.HotelDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.Hotel;

import java.util.List;

public interface HotelService {

    List<HotelDTO> getAllHotels();
    Page<HotelDTO> getAllHotelsPages(Pageable pageable,String keyword, String name);
    HotelDTO getHotelById(Long hotel_id);
    HotelDTO addHotel(HotelDTO hotelDTO);
    HotelDTO updateHotel(HotelDTO hotelDTO);
    void deleteHotel(Long id);
    List<HotelDTO> getHotelByName(String name);
    List<RoomDTO> getRoomsHotel(Long id);


}
