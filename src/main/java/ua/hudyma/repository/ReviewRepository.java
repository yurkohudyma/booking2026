package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByUser_UserId(String userId);

}
