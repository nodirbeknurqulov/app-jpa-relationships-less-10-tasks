package uz.pdp.appjparelationshipsless10tasks.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// Nurkulov Nodirbek 3/7/2022  11:28 AM
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
}
