package uz.pdp.appjparelationshipsless10tasks.repository;
// Nurkulov Nodirbek 3/7/2022  12:31 PM

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationshipsless10tasks.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByNumberAndHotelId(Integer number, Integer hotel_id);
    Page<Room> findRoomsByHotelId(Integer hotel_id, Pageable pageable);
}
