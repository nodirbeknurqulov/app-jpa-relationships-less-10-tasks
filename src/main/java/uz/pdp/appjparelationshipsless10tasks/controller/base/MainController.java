package uz.pdp.appjparelationshipsless10tasks.controller.base;
// Nurkulov Nodirbek 3/7/2022  8:32 PM

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshipsless10tasks.entity.Hotel;
import uz.pdp.appjparelationshipsless10tasks.exception.RecordNotFoundException;
import uz.pdp.appjparelationshipsless10tasks.service.HotelService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final HotelService hotelService;

    @GetMapping("{pageNumber}/{pageSize}/{sortBy}")
    public String getAllHotels(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String sortBy, Model model) {
        List<Hotel> allHotels = hotelService.getAllHotels(pageNumber, pageSize, sortBy);
        model.addAttribute("hotels", allHotels);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortBy", sortBy);
        return "view-hotels";
    }

    @GetMapping("delete/{hotelId}/pageNumber/pageSize/sortBy?{pageNumber}&{pageSize}&{sortBy}")
    public String deleteHotel(@PathVariable Integer hotelId,
                              Model model,
                              @PathVariable Integer pageNumber,
                              @PathVariable Integer pageSize,
                              @PathVariable String sortBy) throws RecordNotFoundException {
        String success = hotelService.deleteHotelById(hotelId);
        model.addAttribute("deleted", success);
        List<Hotel> allHotels = hotelService.getAllHotels(pageNumber, pageSize, sortBy);
        model.addAttribute("hotels", allHotels);
        return "view-hotels";
    }
}
