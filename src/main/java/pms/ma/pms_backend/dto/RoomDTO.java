package pms.ma.pms_backend.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Data @Builder
public class RoomDTO {

    private Long roomId;
    private Long hotelId;
    private int roomNumber;
    private int floorNumber;
    private String type ;
    private int pricePerNight;
    private boolean available;

}