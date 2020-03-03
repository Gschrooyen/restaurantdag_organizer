package be.gschrooyen.restaurantdag.controller;

import be.gschrooyen.restaurantdag.model.Restaurantdag;
import be.gschrooyen.restaurantdag.model.dto.RestaurantdagDto;
import be.gschrooyen.restaurantdag.service.RestaurantdagService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurantdag")
public class RestaurantdagController {

    private final RestaurantdagService restaurantdagService;

    public RestaurantdagController(RestaurantdagService restaurantdagService) {
        this.restaurantdagService = restaurantdagService;
    }

    /**
     * @param restaurantdagDto
     * @return the newly made Restaurantdag
     */
    @Transactional
    @PostMapping("/new")
    public Restaurantdag nieuw(@RequestBody @Valid RestaurantdagDto restaurantdagDto){
        return restaurantdagService.createRestaurantdag(restaurantdagDto.getNaam(), restaurantdagDto.getEpochDate(), restaurantdagDto.getGerechten());
    }
}
