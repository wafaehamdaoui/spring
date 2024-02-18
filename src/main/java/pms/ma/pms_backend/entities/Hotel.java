package pms.ma.pms_backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class Hotel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotel_id;
    private String name;
    private String address;
    private String city;
    private String country;
    private double rating;
    private int num_rooms;
    private int num_floors;
    @OneToMany
    private List<Room> rooms;

}
