package uz.pdp.appjparelationshipsless10tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshipsless10tasks.entity.Hotel;
import uz.pdp.appjparelationshipsless10tasks.exception.RecordNotFoundException;
import uz.pdp.appjparelationshipsless10tasks.payload.HotelDto;
import uz.pdp.appjparelationshipsless10tasks.repository.HotelRepository;
import uz.pdp.appjparelationshipsless10tasks.service.HotelService;

import java.util.List;
import java.util.Optional;

// Nurkulov Nodirbek 3/7/2022  11:33 AM
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

//    @Autowired
//    HotelRepository hotelRepository;

//    @GetMapping
//    public List<Hotel> getHotels() {
//        List<Hotel> hotelList = hotelRepository.findAll();
//        return hotelList;
//    }
//
//    @PostMapping
//    public String addHotel(@RequestBody HotelDto hotelDto) {
//        Hotel hotel = new Hotel();
//        hotel.setName(hotelDto.getName());
//        hotelRepository.save(hotel);
//        return "Hotel added!!!";
//    }
//
//    @PutMapping("/{id}")
//    public String updateHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {
//        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
//        if (optionalHotel.isPresent()) {
//            Hotel hotel = optionalHotel.get();
//            hotel.setName(hotelDto.getName());
//            hotelRepository.save(hotel);
//            return "Hotel edited!!!";
//        }
//        return "Hotel not found!!!";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteHotel(@PathVariable Integer id){
//        hotelRepository.deleteById(id);
//        return "Hotel deleted!!!";
//    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortedBy) {
        List<Hotel> hotelList = hotelService.getAllHotels(pageNo, pageSize, sortedBy);
        return new ResponseEntity<List<Hotel>>(hotelList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        Hotel hotelById = hotelService.getHotelById(id);
        return new ResponseEntity<Hotel>(hotelById, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hotel> createOrUpdateHotel(Hotel hotel) throws RecordNotFoundException {
        Hotel orUpdateHotel = hotelService.createOrUpdateHotel(hotel);
        return new ResponseEntity<Hotel>(orUpdateHotel, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteHotelById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        hotelService.deleteHotelById(id);
        return HttpStatus.FORBIDDEN;
    }
}
