package be.gschrooyen.restaurantdag.controller;

import be.gschrooyen.restaurantdag.model.HoofdGerecht;
import be.gschrooyen.restaurantdag.model.Restaurantdag;
import be.gschrooyen.restaurantdag.model.dto.GerechtDto;
import be.gschrooyen.restaurantdag.model.dto.RestaurantdagCreatedDto;
import be.gschrooyen.restaurantdag.model.dto.RestaurantdagDto;
import be.gschrooyen.restaurantdag.service.RestaurantdagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
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
    @PostMapping("/new")
    public RestaurantdagCreatedDto nieuw(@RequestBody RestaurantdagDto restaurantdagDto, HttpServletRequest request){
        restaurantdagDto.setEpochDate(restaurantdagDto.getEpochDate().plusDays(1));
        Restaurantdag restaurantdag = restaurantdagService.createRestaurantdag(restaurantdagDto.getNaam(), restaurantdagDto.getEpochDate(), restaurantdagDto.getGerechten());
        System.out.println(restaurantdag);
        return new RestaurantdagCreatedDto(restaurantdag.getId(), restaurantdag.getNaam(), restaurantdag.getDatum());
    }

    @GetMapping("/next")
    public ResponseEntity<RestaurantdagDto> getNextRestaurantdag(){
        try {
            Restaurantdag restaurantdag = restaurantdagService.getNext();
            List<GerechtDto> gerechtDtos = restaurantdag.getGerechten().stream().map(gerecht -> {
                if (gerecht instanceof HoofdGerecht) {
                    return new GerechtDto(gerecht.getNaam(), ((HoofdGerecht) gerecht).getPrijs(), ((HoofdGerecht) gerecht).isKinderGerecht(), gerecht.getType());
                }
                return new GerechtDto(gerecht.getNaam(), null, null, gerecht.getType());
            }).collect(Collectors.toList());
            return new ResponseEntity<RestaurantdagDto>(new RestaurantdagDto(restaurantdag.getNaam(), restaurantdag.getDatum(), gerechtDtos), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
