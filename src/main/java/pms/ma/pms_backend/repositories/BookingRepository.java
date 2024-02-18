package pms.ma.pms_backend.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pms.ma.pms_backend.entities.Booking;
import pms.ma.pms_backend.entities.Hotel;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {


    Page<Booking> findByGuestName(String keyword, Pageable pageable);





}