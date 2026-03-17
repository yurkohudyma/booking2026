package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ua.hudyma.util.IdGenerator.generateId;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String roomCode =
            "RM" + generateId(0, 8);

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ToString.Exclude
    @OneToMany(mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookingList = new ArrayList<>();

    Integer maxVisitorsCapacity = 1;

    private BigDecimal cost;

}
