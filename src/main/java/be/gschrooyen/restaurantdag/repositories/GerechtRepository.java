package be.gschrooyen.restaurantdag.repositories;

import be.gschrooyen.restaurantdag.model.Gerecht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GerechtRepository extends JpaRepository<Gerecht, Long> {
}
