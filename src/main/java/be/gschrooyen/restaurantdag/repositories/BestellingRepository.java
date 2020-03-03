package be.gschrooyen.restaurantdag.repositories;

import be.gschrooyen.restaurantdag.model.Bestelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellingRepository extends JpaRepository<Bestelling, Long> {
}
