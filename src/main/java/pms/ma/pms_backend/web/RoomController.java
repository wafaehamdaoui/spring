package pms.ma.pms_backend.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pms.ma.pms_backend.dto.BookingDTO;
import pms.ma.pms_backend.dto.RoomDTO;
import pms.ma.pms_backend.services.RoomService;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/Selectlist")
    public List<RoomDTO> getRoomsByTypeAndHotel(@RequestParam String type, @RequestParam Long hotelId) {
        return roomService.getRoomByTypeAndHotel(type, hotelId);
    }


    @GetMapping("/{room_id}")
    public RoomDTO getRoomById(@PathVariable Long room_id) {
        System.out.println("------------------");
        return roomService.getRoomById(room_id);
    }
    @PostMapping("")
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.addRoom(roomDTO);
    }

    @PutMapping("")
    public RoomDTO updateRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.updateRoom(roomDTO);
    }
    @PatchMapping("/{room_id}")
    public RoomDTO setAvailable(@PathVariable Long room_id) {
        RoomDTO roomDTO = roomService.getRoomById(room_id);
        roomDTO.setAvailable(!roomDTO.isAvailable());
        return roomService.updateRoom(roomDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/number/{num}")
    public List<RoomDTO> getRoomByNumber(@PathVariable int num) {
        return roomService.getRoomByNumber(num);
    }

    @GetMapping("/{id}/bookings")
    public List<BookingDTO> getBookingsRoom(@PathVariable Long id) {
        return roomService.getBookingsRoom(id);
    }


    @GetMapping("/pages")
    public Page<RoomDTO> getAllHotelsPages(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int per_page,
                                            @RequestParam(defaultValue = "") String keyword,
                                            @RequestParam(defaultValue = "") String value,
                                            @RequestParam(defaultValue = "roomNumber") String sortBy) {
        Pageable pageable = PageRequest.of(page, per_page, Sort.by(sortBy));
        if (value != null && !value.isEmpty()) {
            return roomService.getRoomByAttributteAndPagenation(pageable,keyword,value);
        }else{
        return roomService.getAllRoomsAndPagenation(pageable);
        }
    }

}
