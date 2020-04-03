package be.gschrooyen.restaurantdag.repositories;

import be.gschrooyen.restaurantdag.model.Gerecht;
import be.gschrooyen.restaurantdag.model.Restaurantdag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GerechtRepository extends JpaRepository<Gerecht, Long> {

    List<Gerecht> getAllByRestaurantdag(Restaurantdag restaurantdag);
}
