package pms.ma.pms_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data @Builder
public class Room {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private Long hotelId;
    private int roomNumber;
    private int floorNumber;
    private String type ;
    private int pricePerNight;
    private boolean available;

    @ManyToOne
    private Hotel hotel;

    @OneToMany
    private List<Booking> bookings;

}
