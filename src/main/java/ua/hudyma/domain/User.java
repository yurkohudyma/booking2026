package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ua.hudyma.enums.GeniusLevel;
import ua.hudyma.enums.Sex;
import ua.hudyma.enums.UserType;
import ua.hudyma.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ua.hudyma.util.IdGenerator.generateId;
import static ua.hudyma.util.IdGenerator.generatePhoneNumber;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String phone = generatePhoneNumber();

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(unique = true)
    private String email; //generate from name

    private String name;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @Enumerated(EnumType.STRING)
    private GeniusLevel geniusLevel;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Transaction> transactionList = new ArrayList<>();

}
