package pms.ma.pms_backend.mappers;

import org.springframework.stereotype.Component;
import pms.ma.pms_backend.dto.HotelDTO;
import pms.ma.pms_backend.entities.Hotel;

@Component
public class HotelMapper {

    public HotelDTO fromHotelTOHotelDTO(Hotel hotel) {
        return HotelDTO.builder()
                .hotel_id(hotel.getHotel_id())
                .address(hotel.getAddress())
                .name(hotel.getName())
                .city(hotel.getCity())
                .country(hotel.getCountry())
                .num_floors(hotel.getNum_floors())
                .num_rooms(hotel.getNum_rooms())
                .rating(hotel.getRating())
                .build();
    }

    public Hotel fromHotelDTOToHotel(HotelDTO hotelDTO) {
        return Hotel.builder()
                .hotel_id(hotelDTO.getHotel_id())
                .address(hotelDTO.getAddress())
                .name(hotelDTO.getName())
                .city(hotelDTO.getCity())
                .country(hotelDTO.getCountry())
                .num_floors(hotelDTO.getNum_floors())
                .num_rooms(hotelDTO.getNum_rooms())
                .rating(hotelDTO.getRating())
                .build();
    }

}
