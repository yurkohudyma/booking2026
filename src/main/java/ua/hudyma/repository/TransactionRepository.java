package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
