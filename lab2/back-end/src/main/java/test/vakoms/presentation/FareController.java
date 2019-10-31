package test.vakoms.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.vakoms.application.FareService;
import test.vakoms.domain.Fare;
import test.vakoms.dto.FareDTO;
import test.vakoms.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = "https://lab2front-256619.appspot.com")
@RestController
public class FareController {

    @Autowired
    private FareService fareService;

    @GetMapping("/fares")
    public List<FareDTO> getAllFares() {
        List<Fare> fares = fareService.getAllFares();
        Link link = linkTo(methodOn(FareController.class).getAllFares()).withSelfRel();

        List<FareDTO> fareDTOList = new ArrayList<>();
        for (Fare entity : fares) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            FareDTO dto = new FareDTO(entity, selfLink);
            fareDTOList.add(dto);
        }

        return fareDTOList;
    }

    @GetMapping("/fares/{fareId}")
    public ResponseEntity<FareDTO> getFare(@PathVariable Integer fareId) throws ResourceNotFoundException {
        Link link = linkTo(methodOn(FareController.class).getFare(fareId)).withSelfRel();

        return ResponseEntity.ok().body(new FareDTO(fareService.getFare(fareId), link));
    }

    @GetMapping(value = "/users/{userId}/fares")
    public ResponseEntity<Set<FareDTO>> getFaresByUserId(@PathVariable Integer userId) {
        Set<Fare> fares = fareService.getFaresByUserId(userId);

        Link link = linkTo(methodOn(FareController.class).getAllFares()).withSelfRel();

        Set<FareDTO> fareSet = new HashSet<>();
        for (Fare entity : fares) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            FareDTO dto = new FareDTO(entity, selfLink);
            fareSet.add(dto);
        }

        return new ResponseEntity<>(fareSet, HttpStatus.OK);
    }

    @GetMapping("/search/fares/{uuid}")
    public Set<FareDTO> findAllFaresByUuid(@PathVariable String uuid) {
        Set<Fare> fares = fareService.findAllFaresByUuid(uuid);
        Link link = linkTo(methodOn(FareController.class).getAllFares()).withSelfRel();

        Set<FareDTO> fareDTOSet = new HashSet<>();
        for (Fare entity : fares) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            FareDTO dto = new FareDTO(entity, selfLink);
            fareDTOSet.add(dto);
        }

        return fareDTOSet;
    }

    @PostMapping("/fares")
    public ResponseEntity<FareDTO> addFare(@RequestBody Fare fare) {
        fareService.addFare(fare);

        Link link = linkTo(methodOn(FareController.class).getFaresByUserId(fare.getId())).withSelfRel();

        return new ResponseEntity<>(new FareDTO(fare, link), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/fares/{fareId}")
    public ResponseEntity<FareDTO> assignFareToUser(@PathVariable Integer fareId, @PathVariable Integer userId) throws ResourceNotFoundException {
        fareService.assignFareToUser(fareId, userId);

        Link link = linkTo(methodOn(FareController.class).getFare(fareId)).withSelfRel();

        return new ResponseEntity<>(new FareDTO(fareService.getFare(fareId), link), HttpStatus.OK);
    }

    @PutMapping("/fares/{fareId}")
    public ResponseEntity<FareDTO> updateFare(@PathVariable Integer fareId, @RequestBody Fare fare) throws ResourceNotFoundException {
        fareService.updateFare(fareId, fare);

        Fare updatedFare = fareService.getFare(fareId);
        Link link = linkTo(methodOn(FareController.class).getFare(fareId)).withSelfRel();

        return new ResponseEntity<>(new FareDTO(updatedFare, link), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/fares/{fareId}")
    public ResponseEntity<FareDTO> updateFareUser(@PathVariable Integer fareId, @PathVariable Integer userId) throws ResourceNotFoundException {
        fareService.updateFareUser(fareId, userId);

        Fare updatedFare = fareService.getFare(fareId);
        Link link = linkTo(methodOn(FareController.class).getFare(fareId)).withSelfRel();

        return new ResponseEntity<>(new FareDTO(updatedFare, link), HttpStatus.OK);
    }

    @DeleteMapping("/fares/{fareId}")
    public ResponseEntity deleteFare(@PathVariable Integer fareId) {
        fareService.deleteFare(fareId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/fares/{fareId}")
    public ResponseEntity deleteFareUser(@PathVariable Integer fareId) {
        fareService.deleteFareUser(fareId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
