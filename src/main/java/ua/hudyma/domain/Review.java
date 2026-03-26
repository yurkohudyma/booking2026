package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;

import static ua.hudyma.util.IdGenerator.generateId;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewCode = generateId(4,8);

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    Integer rating;

    String details;

}
