package be.gschrooyen.restaurantdag.service;

import be.gschrooyen.restaurantdag.model.Gerecht;
import be.gschrooyen.restaurantdag.repositories.GerechtRepository;
import org.springframework.stereotype.Service;

@Service
public class GerechtService {
    private final GerechtRepository gerechtRepository;

    public GerechtService(GerechtRepository gerechtRepository) {
        this.gerechtRepository = gerechtRepository;
    }

    public Gerecht getById(long gerechtId){
        return gerechtRepository.getOne(gerechtId);
    }
}
