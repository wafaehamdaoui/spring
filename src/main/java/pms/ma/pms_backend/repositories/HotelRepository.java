package pms.ma.pms_backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pms.ma.pms_backend.entities.Hotel;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findHotelByName(String name);
    Page<Hotel> findHotelByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Hotel> findHotelByCityContainingIgnoreCase(String name, Pageable pageable);
    Page<Hotel> findHotelByCountryContainingIgnoreCase(String name, Pageable pageable);

}
