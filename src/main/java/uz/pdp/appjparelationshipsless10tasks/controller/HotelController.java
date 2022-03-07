package uz.pdp.appjparelationshipsless10tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshipsless10tasks.entity.Hotel;
import uz.pdp.appjparelationshipsless10tasks.payload.HotelDto;
import uz.pdp.appjparelationshipsless10tasks.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

// Nurkulov Nodirbek 3/7/2022  11:33 AM
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }

    @PostMapping
    public String addHotel(@RequestBody HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotelRepository.save(hotel);
        return "Hotel added!!!";
    }

    @PutMapping("/{id}")
    public String updateHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setName(hotelDto.getName());
            hotelRepository.save(hotel);
            return "Hotel edited!!!";
        }
        return "Hotel not found!!!";
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "Hotel deleted!!!";
    }
}
