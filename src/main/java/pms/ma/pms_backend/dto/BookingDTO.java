package pms.ma.pms_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long bookingId;
    private Long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private String guestName;
    private String guestEmail;
    private double totalPrice;
}

