package pms.ma.pms_backend.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pms.ma.pms_backend.dto.HotelDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.entities.User;
import pms.ma.pms_backend.services.HotelService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }


    @GetMapping("/hotels/pages")
    public Page<HotelDTO> getAllHotelsPages(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int per_page,
                                            @RequestParam(defaultValue = "") String keyword,
                                            @RequestParam(defaultValue = "") String value,
                                            @RequestParam(defaultValue = "name") String sortBy) {
        Pageable pageable = PageRequest.of(page, per_page, Sort.by(sortBy));
        return hotelService.getAllHotelsPages(pageable, keyword, value);
    }

    @GetMapping("/hotels/{hotel_id}")
    public HotelDTO getHotelById(@PathVariable Long hotel_id) {
        return hotelService.getHotelById(hotel_id);
    }

    @PostMapping("/hotels")
    public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO) {
        return hotelService.addHotel(hotelDTO);
    }

    @PutMapping("/hotels")
    public HotelDTO updateHotel(@RequestBody HotelDTO hotelDTO) {
        return hotelService.updateHotel(hotelDTO);
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/hotels/name/{name}")
    public List<HotelDTO> getHotelByName(@PathVariable String name) {
        return hotelService.getHotelByName(name);
    }

    @GetMapping("/hotels/{id}/rooms")
    public List<RoomDTO> getRoomsHotel(@PathVariable Long id) {
        return hotelService.getRoomsHotel(id);
    }

}
