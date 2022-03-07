package uz.pdp.appjparelationshipsless10tasks.service;
// Nurkulov Nodirbek 3/7/2022  6:34 PM

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.appjparelationshipsless10tasks.entity.Hotel;
import uz.pdp.appjparelationshipsless10tasks.exception.RecordNotFoundException;
import uz.pdp.appjparelationshipsless10tasks.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    //GET ALL HOTELS
    public List<Hotel> getAllHotels(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        if (hotelPage.hasContent()) return hotelPage.getContent();
        else return new ArrayList<Hotel>();
    }

    //GET HOTEL BY ID
    public Hotel getHotelById(Integer id) throws RecordNotFoundException {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            return hotel;
        } else throw new RecordNotFoundException("No hotel record exist for given id");
    }

    //CREATE OR UPDATE HOTEL
    public Hotel createOrUpdateHotel(Hotel hotel) throws RecordNotFoundException {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotel.getId());
        if (optionalHotel.isPresent()) {
            Hotel newHotel = optionalHotel.get();
            newHotel.setName(hotel.getName());
            newHotel = hotelRepository.save(newHotel);
            return newHotel;
        } else {
            hotel = hotelRepository.save(hotel);
            return hotel;
        }
    }

    //DELETE HOTEL BY ID
    public String deleteHotelById(Integer id) throws RecordNotFoundException {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            hotelRepository.deleteById(id);
            return "Successfully deleted!";
        } else {
            throw new RecordNotFoundException("No hotel record exist for given id");
        }
    }
}
