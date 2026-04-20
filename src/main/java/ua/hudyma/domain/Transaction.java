package ua.hudyma.domain;

import jakarta.persistence.*;
import lombok.Data;
import ua.hudyma.enums.TransactionStatus;

import java.math.BigDecimal;

import static ua.hudyma.enums.TransactionStatus.PENDING;

@Data
@Table(name = "transactions")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36)
    private String transactionId;

    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    private final TransactionStatus transactionStatus = PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
