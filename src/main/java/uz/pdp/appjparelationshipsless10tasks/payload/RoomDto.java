package uz.pdp.appjparelationshipsless10tasks.payload;

import lombok.Data;

// Nurkulov Nodirbek 3/7/2022  12:31 PM
@Data
public class RoomDto {
    private Integer number;
    private String floor;
    private double size;
    private Integer hotelId;

//    {
//        "number":1,
//            "floor":"first",
//            "size":12.34,
//            "hotelId":1
//    }
}
