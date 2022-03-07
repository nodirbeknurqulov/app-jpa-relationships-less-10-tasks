package uz.pdp.appjparelationshipsless10tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import uz.pdp.appjparelationshipsless10tasks.entity.Hotel;

public interface HotelRepository extends PagingAndSortingRepository<Hotel,Integer> {

}
