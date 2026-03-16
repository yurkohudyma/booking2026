package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import ua.hudyma.dto.Geolocation;
import ua.hudyma.enums.PropertyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static ua.hudyma.util.IdGenerator.generateId;

@Entity
@Table
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String propertyId = generateId(4,8);

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

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json", name = "geolocation")
    private Geolocation geolocation;

    private BigDecimal rating = BigDecimal.ZERO;
    //to be recalculated
    // upon visitors review assessment


}
