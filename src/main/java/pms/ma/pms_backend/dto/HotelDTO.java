package pms.ma.pms_backend.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class HotelDTO {

    private Long hotel_id;
    private String name;
    private String address;
    private String city;
    private String country;
    private double rating;
    private int num_rooms;
    private int num_floors;

}