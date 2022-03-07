package uz.pdp.appjparelationshipsless10tasks.entity;

import lombok.*;

import javax.persistence.*;

// Nurkulov Nodirbek 3/7/2022  11:30 AM
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private String floor;

    private double size;

    @ManyToOne
    private Hotel hotel;
}
