package uz.pdp.appjparelationshipsless10tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshipsless10tasks.entity.Hotel;
import uz.pdp.appjparelationshipsless10tasks.entity.Room;
import uz.pdp.appjparelationshipsless10tasks.payload.RoomDto;
import uz.pdp.appjparelationshipsless10tasks.repository.HotelRepository;
import uz.pdp.appjparelationshipsless10tasks.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

// Nurkulov Nodirbek 3/7/2022  12:28 PM
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    //bu oddiy xolatda mehmonxonadagi xonalar royxatini olish edi
    @GetMapping
    public List<Room> getRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList;
    }

    //BUNISI ESA PAGEABLE ORQALI OLISH
    @GetMapping("/forManager")
    public Page<Room> getRoomListForManager(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }

    @GetMapping("/forManager/{hotelId}")
    public Page<Room> getRoomsByHotelId(@RequestParam int page, @PathVariable Integer hotelId) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Room> roomsByHotelId = roomRepository.findRoomsByHotelId(hotelId, pageable);
        return roomsByHotelId;
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        boolean exists = roomRepository.existsByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId());
        if (exists)
            return "This hotel has such kind of room";
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()) {
            return "Hotel not found!!!";
        }
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);

        roomRepository.save(room);
        return "Room added!!!";
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());

            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
            if (optionalHotel.isPresent()) {
                Hotel hotel = optionalHotel.get();
                room.setHotel(hotel);
            } else return "Hotel not found";
            roomRepository.save(room);
            return "Room updated!!!";
        }
        return "Room not found!!!";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        try {
            roomRepository.deleteById(id);
            return "Room deleted!!!";
        } catch (Exception e) {
            return "Error in deleting!!!";
        }
    }
}
