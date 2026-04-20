package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import ua.hudyma.dto.Geolocation;
import ua.hudyma.enums.PropertyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ua.hudyma.util.IdGenerator.generateId;

@Entity
@Table
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String propertyCode = generateId(4,8);

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @CreationTimestamp
    private LocalDateTime registeredOn;

    @UpdateTimestamp
    private LocalDateTime renewedOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String address;

    private LocalTime checkin;

    private LocalTime checkout;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json", name = "geolocation")
    private Geolocation geolocation;

    private Double distanceFromCenter;

    private BigDecimal rating = BigDecimal.ZERO;

    @ToString.Exclude
    @OneToMany(mappedBy = "property",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookingList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "property",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Room> roomList = new ArrayList<>();
}