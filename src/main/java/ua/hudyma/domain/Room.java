package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;

import static ua.hudyma.util.IdGenerator.generateId;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String roomId = "RM" + generateId(0,8);

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    Integer maxVisitorsCapacity;

    private BigDecimal cost;

}
