package be.gschrooyen.restaurantdag.repositories;

import be.gschrooyen.restaurantdag.model.Restaurantdag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantdagRepository extends JpaRepository<Restaurantdag, Long> {
}
