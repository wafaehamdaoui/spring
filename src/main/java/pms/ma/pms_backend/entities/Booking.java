package pms.ma.pms_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    private Long roomId;
    @ManyToOne
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private String guestName;
    private String guestEmail;
    private double totalPrice;

}
