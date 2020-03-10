package be.gschrooyen.restaurantdag.controller;

import be.gschrooyen.restaurantdag.model.Bestelling;
import be.gschrooyen.restaurantdag.model.HoofdGerecht;
import be.gschrooyen.restaurantdag.model.Inschrijving;
import be.gschrooyen.restaurantdag.model.Restaurantdag;
import be.gschrooyen.restaurantdag.model.dto.*;
import be.gschrooyen.restaurantdag.service.RestaurantdagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
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
    public RestaurantdagCreatedDto nieuw(@RequestBody RestaurantdagDto restaurantdagDto, HttpServletRequest request) {
        restaurantdagDto.setEpochDate(restaurantdagDto.getEpochDate().plusDays(1));
        Restaurantdag restaurantdag = restaurantdagService.createRestaurantdag(restaurantdagDto.getNaam(), restaurantdagDto.getEpochDate(), restaurantdagDto.getGerechten());
        System.out.println(restaurantdag);
        return new RestaurantdagCreatedDto(restaurantdag.getId(), restaurantdag.getNaam(), restaurantdag.getDatum());
    }

    @GetMapping("/next")
    public ResponseEntity<RestaurantdagDto> getNextRestaurantdag() {
        try {
            Restaurantdag restaurantdag = restaurantdagService.getNext();
            List<GerechtDto> gerechtDtos = restaurantdag.getGerechten().stream().map(gerecht -> {
                if (gerecht instanceof HoofdGerecht) {
                    return new GerechtDto(gerecht.getNaam(), ((HoofdGerecht) gerecht).getPrijs(), ((HoofdGerecht) gerecht).isKinderGerecht(), gerecht.getType());
                }
                return new GerechtDto(gerecht.getNaam(), null, null, gerecht.getType());
            }).collect(Collectors.toList());
            return new ResponseEntity<RestaurantdagDto>(new RestaurantdagDto(restaurantdag.getNaam(), restaurantdag.getDatum(), gerechtDtos, restaurantdag.getInschrijvingen().size()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantdagOverzichtDto>> getAll() {
        List<RestaurantdagOverzichtDto> overzicht = restaurantdagService.getAll().stream().map(restaurantdag -> new RestaurantdagOverzichtDto(restaurantdag.getNaam(), restaurantdag.getDatum().format(DateTimeFormatter.ISO_DATE), restaurantdag.getInschrijvingen().size(), calculatePersonen(restaurantdag.getInschrijvingen()))).collect(Collectors.toList());
        return ResponseEntity.ok(overzicht);
    }

    @GetMapping("/inschrijvingen/{id}")
    private ResponseEntity<List<InschrijvingDto>> getInschrijvingen(@PathVariable("id") String id) {
        try {
            Restaurantdag restaurantdag = null;
            if (id.equals("current")) {
                restaurantdag = restaurantdagService.getNext();
            } else {
                restaurantdag = restaurantdagService.getRestaurantdag(Long.parseLong(id));
            }
            List<Inschrijving> inschrijvings = restaurantdag.getInschrijvingen();
            if (inschrijvings.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(inschrijvings.stream().map(inschrijving -> new InschrijvingDto(inschrijving.getId(), inschrijving.getNaam(), inschrijving.getGroep(), convertBestellingen(inschrijving.getBestellingen()), inschrijving.getTijdstip().format(DateTimeFormatter.ISO_TIME))).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private List<BestellingDto> convertBestellingen(List<Bestelling> bestellingen) {
        return bestellingen.stream().map(bestelling -> {
            if (bestelling.getGerecht() instanceof HoofdGerecht) {
                String naam = bestelling.getGerecht().getNaam();
                if (((HoofdGerecht) bestelling.getGerecht()).isKinderGerecht()) {
                    naam += " (kindergerecht)";
                }
                return new BestellingDto(naam, bestelling.getAantal());
            } else {
                return new BestellingDto(bestelling.getGerecht().getNaam(), bestelling.getAantal());
            }
        }).collect(Collectors.toList());
    }

    private double calculatePersonen(List<Inschrijving> inschrijvings) {
        double totaal = 0;
        for (Inschrijving inschrijving : inschrijvings) {
            for (Bestelling bestelling : inschrijving.getBestellingen()) {
                if (bestelling.getGerecht() instanceof HoofdGerecht) {
                    if (((HoofdGerecht) bestelling.getGerecht()).isKinderGerecht()) {
                        totaal += (bestelling.getAantal() / 2D);
                    } else {
                        totaal += bestelling.getAantal();
                    }
                }
            }
        }
        return totaal;
    }
}
