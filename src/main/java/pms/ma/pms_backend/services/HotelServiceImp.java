package pms.ma.pms_backend.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pms.ma.pms_backend.dto.HotelDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.Hotel;
import pms.ma.pms_backend.exceptions.HotelNotFoundException;
import pms.ma.pms_backend.mappers.HotelMapper;
import pms.ma.pms_backend.mappers.RoomMapper;
import pms.ma.pms_backend.repositories.HotelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelServiceImp implements HotelService{

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final RoomMapper roomMapper;

    @Override
    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(hotel -> hotelMapper.fromHotelTOHotelDTO(hotel)).collect(Collectors.toList());
    }

    @Override
    public Page<HotelDTO> getAllHotelsPages(Pageable pageable, String keyword, String value) {
        Page<Hotel> hotels;
        switch (keyword) {
            case "name":
                hotels = hotelRepository.findHotelByNameContainingIgnoreCase(value, pageable);
                break;
            case "city":
                hotels = hotelRepository.findHotelByCityContainingIgnoreCase(value, pageable);
                break;
            case "country":
                hotels = hotelRepository.findHotelByCountryContainingIgnoreCase(value, pageable);
                break;
            default:
                hotels = hotelRepository.findHotelByNameContainingIgnoreCase(value, pageable);
        }

        List<HotelDTO> hotelDTOList = hotels.getContent().stream()
                .map(hotelMapper::fromHotelTOHotelDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(hotelDTOList, pageable, hotels.getTotalElements());
    }

    @Override
    public HotelDTO getHotelById(Long hotel_id) {

        Hotel hotel = hotelRepository.findById(hotel_id).orElseThrow(
                () -> new HotelNotFoundException("Hotel not found")
        );
        return hotelMapper.fromHotelTOHotelDTO(hotel);
    }

    @Override
    public HotelDTO addHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.save(hotelMapper.fromHotelDTOToHotel(hotelDTO));
        return hotelMapper.fromHotelTOHotelDTO(hotel);
    }

    @Override
    public HotelDTO updateHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(hotelDTO.getHotel_id()).orElseThrow(
                () -> new HotelNotFoundException("Hotel not found")
        );
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setRating(hotelDTO.getRating());
        hotel.setNum_rooms(hotelDTO.getNum_rooms());
        hotel.setNum_floors(hotelDTO.getNum_floors());
        return hotelMapper.fromHotelTOHotelDTO(hotel);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<HotelDTO> getHotelByName(String name) {
        List<Hotel> hotels = hotelRepository.findHotelByName(name);
        return hotels.stream().map(hotel -> hotelMapper.fromHotelTOHotelDTO(hotel)).collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getRoomsHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new HotelNotFoundException("Hotel not found")
        );
        List<RoomDTO> rooms = hotel.getRooms().stream().map(room -> roomMapper.fromRoomToRoomDTO(room)).collect(Collectors.toList());
        return rooms;
    }
}
