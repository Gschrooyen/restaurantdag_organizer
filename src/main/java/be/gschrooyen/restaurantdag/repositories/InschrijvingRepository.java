package be.gschrooyen.restaurantdag.repositories;

import be.gschrooyen.restaurantdag.model.Inschrijving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InschrijvingRepository extends JpaRepository<Inschrijving, Long> {
}
