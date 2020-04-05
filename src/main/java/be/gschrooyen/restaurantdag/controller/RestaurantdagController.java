package be.gschrooyen.restaurantdag.controller;

import be.gschrooyen.restaurantdag.model.*;
import be.gschrooyen.restaurantdag.model.dto.*;
import be.gschrooyen.restaurantdag.service.InschrijvingService;
import be.gschrooyen.restaurantdag.service.RestaurantdagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/restaurantdag")
public class RestaurantdagController {

    private final RestaurantdagService restaurantdagService;
    private final InschrijvingService inschrijvingService;

    public RestaurantdagController(RestaurantdagService restaurantdagService, InschrijvingService inschrijvingService) {
        this.restaurantdagService = restaurantdagService;
        this.inschrijvingService = inschrijvingService;
    }

    @PostMapping("/new")
    public RestaurantdagCreatedDto nieuw(@RequestBody RestaurantdagDto restaurantdagDto, HttpServletRequest request) {
        restaurantdagDto.setEpochDate(restaurantdagDto.getEpochDate().plusDays(1));
        Restaurantdag restaurantdag = restaurantdagService.createRestaurantdag(restaurantdagDto.getNaam(), restaurantdagDto.getEpochDate(), restaurantdagDto.getGerechten());
        return new RestaurantdagCreatedDto(restaurantdag.getId(), restaurantdag.getNaam(), restaurantdag.getDatum());
    }

    @GetMapping("/next")
    public ResponseEntity<RestaurantdagDto> getNextRestaurantdag() {
        try {
            Restaurantdag restaurantdag = restaurantdagService.getNext();
            List<GerechtDto> gerechtDtos = restaurantdag.getGerechten().stream().map(gerecht -> {
                if (gerecht instanceof HoofdGerecht) {
                    return new GerechtDto(gerecht.getNaam(), ((HoofdGerecht) gerecht).getPrijs(), ((HoofdGerecht) gerecht).isKinderGerecht(), gerecht.getType(), gerecht.getId());
                }
                return new GerechtDto(gerecht.getNaam(), null, null, gerecht.getType(), gerecht.getId());
            }).collect(Collectors.toList());
            return new ResponseEntity<RestaurantdagDto>(new RestaurantdagDto(restaurantdag.getNaam(), restaurantdag.getDatum(), gerechtDtos, restaurantdag.getInschrijvingen().size(), restaurantdag.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantdagOverzichtDto>> getAll() {
        List<RestaurantdagOverzichtDto> overzicht =
                restaurantdagService
                        .getAll()
                        .stream()
                        .map(restaurantdag -> new RestaurantdagOverzichtDto(restaurantdag.getNaam(), restaurantdag.getDatum().format(DateTimeFormatter.ISO_DATE), restaurantdag.getInschrijvingen().size(), calculateVolwassenen(restaurantdag.getInschrijvingen()), restaurantdag.getId())).collect(Collectors.toList());
        return ResponseEntity.ok(overzicht);
    }

    @GetMapping("/inschrijvingen/{id}")
    private ResponseEntity<List<InschrijvingDto>> getInschrijvingen(@PathVariable("id") String id) {
        try {
            List<Inschrijving> inschrijvings = new ArrayList<>();
            Restaurantdag restaurantdag = null;
            if (id.equals("current")) {
                restaurantdag = restaurantdagService.getNext();
            } else {
                restaurantdag = restaurantdagService.getRestaurantdag(Long.parseLong(id));
            }
            inschrijvings = restaurantdag.getInschrijvingen();
            return ResponseEntity.ok(inschrijvings.stream().map(inschrijving -> new InschrijvingDto(inschrijving.getId(), inschrijving.getNaam(), inschrijving.getGroep(), inschrijving.getTijdstip().getHour() + ":" + ((inschrijving.getTijdstip().getMinute() < 10) ? "0" + inschrijving.getTijdstip().getMinute() : inschrijving.getTijdstip().getMinute()), calculateVolwassenen(List.of(inschrijving)), calculateKinderen(List.of(inschrijving)))).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantdagDto> getById(@PathVariable long id){
        try {
            Restaurantdag restaurantdag = restaurantdagService.getById(id);
            List<GerechtDto> gerechtDtos = restaurantdag.getGerechten().stream().map(gerecht -> {
                if (gerecht instanceof HoofdGerecht) {
                    return new GerechtDto(gerecht.getNaam(), ((HoofdGerecht) gerecht).getPrijs(), ((HoofdGerecht) gerecht).isKinderGerecht(), gerecht.getType(), gerecht.getId());
                }
                return new GerechtDto(gerecht.getNaam(), null, null, gerecht.getType(), gerecht.getId());
            }).collect(Collectors.toList());
            return new ResponseEntity<RestaurantdagDto>(new RestaurantdagDto(restaurantdag.getNaam(), restaurantdag.getDatum(), gerechtDtos, restaurantdag.getInschrijvingen().size(), restaurantdag.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<RestaurantdagCreatedDto> update(@RequestBody RestaurantdagDto restaurantdagDto) {
        restaurantdagDto.setEpochDate(restaurantdagDto.getEpochDate().plusDays(1));
        Restaurantdag restaurantdag = restaurantdagService.update(restaurantdagDto.getNaam(), restaurantdagDto.getEpochDate(), restaurantdagDto.getGerechten(), restaurantdagDto.getId());
        return ResponseEntity.ok(new RestaurantdagCreatedDto(restaurantdag.getId(), restaurantdag.getNaam(), restaurantdag.getDatum()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id){
        restaurantdagService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/setupBestelling/{id}")
    public ResponseEntity<List<BestellingDto>> setupBestelling(@PathVariable String id){
        Restaurantdag r = null;
        if ("current".equals(id)){
            r = restaurantdagService.getNext();
        } else {
            r = restaurantdagService.getById(Long.parseLong(id));
        }
        List<BestellingDto> bestellingDtos = new ArrayList<>();
        for (Gerecht gerecht : r.getGerechten()) {
            if (gerecht instanceof HoofdGerecht && ((HoofdGerecht) gerecht).isKinderGerecht()){
                bestellingDtos.add(new BestellingDto(gerecht.getNaam()+ "(kindergerecht)", 0, gerecht.getId(), ((HoofdGerecht) gerecht).getPrijs()));
            }else if (gerecht instanceof HoofdGerecht){
                bestellingDtos.add(new BestellingDto(gerecht.getNaam(), 0, gerecht.getId(), ((HoofdGerecht) gerecht).getPrijs()));
            }else {
                bestellingDtos.add(new BestellingDto(gerecht.getNaam(), 0, gerecht.getId(), 0));
            }
        }
        return ResponseEntity.ok(bestellingDtos);
    }


    @PostMapping("/newInschrijving/{restaurantdagId}")
    public ResponseEntity<InschrijvingDto> newInschrijving(@RequestBody NewInschrijvingDto newInschrijvingDto, @PathVariable String restaurantdagId){
        long rid = "current".equals(restaurantdagId)? restaurantdagService.getNext().getId() : Long.parseLong(restaurantdagId);
        Inschrijving i = inschrijvingService.nieuw(newInschrijvingDto.getNaam(), newInschrijvingDto.getGroep(), newInschrijvingDto.getTijdstip(), newInschrijvingDto.getBestelling(), rid);
        return ResponseEntity.ok(new InschrijvingDto(i.getId(), i.getNaam(), i.getGroep(), i.getTijdstip().getHour() + ":" + ((i.getTijdstip().getMinute() < 10) ? "0" + i.getTijdstip().getMinute() : i.getTijdstip().getMinute()), calculateVolwassenen(List.of(i)), calculateKinderen(List.of(i))));
    }

    private List<BestellingDto> convertBestellingen(List<Bestelling> bestellingen) {
        return bestellingen.stream().map(bestelling -> {
            if (bestelling.getGerecht() instanceof HoofdGerecht) {
                String naam = bestelling.getGerecht().getNaam();
                if (((HoofdGerecht) bestelling.getGerecht()).isKinderGerecht()) {
                    naam += " (kindergerecht)";
                }
                return new BestellingDto(naam, bestelling.getAantal(), bestelling.getGerecht().getId(), ((HoofdGerecht) bestelling.getGerecht()).getPrijs());
            } else {
                return new BestellingDto(bestelling.getGerecht().getNaam(), bestelling.getAantal(), bestelling.getGerecht().getId(), 0);
            }
        }).collect(Collectors.toList());
    }

    private double calculateVolwassenen(List<Inschrijving> inschrijvings) {
        double totaal = 0;
        for (Inschrijving inschrijving : inschrijvings) {
            for (Bestelling bestelling : inschrijving.getBestellingen()) {
                if (bestelling.getGerecht() instanceof HoofdGerecht) {
                    if (!((HoofdGerecht) bestelling.getGerecht()).isKinderGerecht()) {
                        totaal += (bestelling.getAantal());
                    }
                }
            }
        }
        return totaal;
    }

    private double calculateKinderen(List<Inschrijving> inschrijvings) {
        double totaal = 0;
        for (Inschrijving inschrijving : inschrijvings) {
            for (Bestelling bestelling : inschrijving.getBestellingen()) {
                if (bestelling.getGerecht() instanceof HoofdGerecht) {
                    if (((HoofdGerecht) bestelling.getGerecht()).isKinderGerecht()) {
                        totaal += (bestelling.getAantal());
                    }
                }
            }
        }
        return totaal;
    }
}
