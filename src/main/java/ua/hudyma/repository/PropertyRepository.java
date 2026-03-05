package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}
