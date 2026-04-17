package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Property;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByPropertyCode(String propertyId);

    List<Property> findAllByRatingGreaterThanEqual(BigDecimal rating);

}
